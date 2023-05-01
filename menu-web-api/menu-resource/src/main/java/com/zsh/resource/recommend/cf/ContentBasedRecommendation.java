package com.zsh.resource.recommend.cf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.zsh.common.utils.DateUtils;
import com.zsh.resource.constant.DishConstant;
import com.zsh.resource.domain.MemberDishLog;
import com.zsh.resource.mapper.MemberDishLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zsh on 2023/4/30
 *
 * @author zsh
 */
@Slf4j
@Component
public class ContentBasedRecommendation {

    private final MemberDishLogMapper memberDishLogMapper;
    private final RedisTemplate<Object, Object> redisTemplate;
    public ContentBasedRecommendation(MemberDishLogMapper memberDishLogMapper, RedisTemplate<Object, Object> redisTemplate) {
        this.memberDishLogMapper = memberDishLogMapper;
        this.redisTemplate = redisTemplate;
    }
    // 菜谱特征矩阵
    private final Map<String, Map<String, Double>> featureMatrix = new HashMap<>();
    private final String fileName = System.getProperty("user.dir") + "/menu-resource/src/main/java/com/zsh/resource/recommend/data/dish_ratings.dat";

    private Map<String, Integer> clickCount = new HashMap<>();
    private Map<String, Integer> upCount = new HashMap<>();
    private Map<String, Integer> collectCount = new HashMap<>();


    /**
     * 添加一个菜谱的特征
     *
     * @param recipeId    菜谱id
     * @param name        菜谱名字
     * @param ingredients 菜谱材料
     * @param steps       菜谱烹饪步骤
     * @param tags        菜谱标签
     */
    public void addRecipeFeature(String recipeId, String name, List<String> ingredients, List<String> steps, List<String> tags) {
        Map<String, Double> recipeFeatures = new HashMap<>();
        // 将菜谱名字拆分成单词，将每个单词作为一个特征
        for (String word : name.split(" ")) {
            recipeFeatures.put("name_" + word.toLowerCase(), 1.0);
        }
        // 将每个材料作为一个特征
        for (String ingredient : ingredients) {
            recipeFeatures.put("ingredient_" + ingredient.toLowerCase(), 1.0);
        }
        // 将每个步骤作为一个特征
        for (String step : steps) {
            recipeFeatures.put("step_" + step.toLowerCase(), 1.0);
        }
        // 将每个标签作为一个特征
        for (String tag : tags) {
            recipeFeatures.put("tag_" + tag.toLowerCase(), 1.0);
        }
        featureMatrix.put(recipeId, recipeFeatures);
    }

    /**
     * 计算两个菜谱的相似度
     *
     * @param recipeId1 菜谱1的id
     * @param recipeId2 菜谱2的id
     * @return 两个菜谱的相似度
     */
    public double calculateSimilarity(String recipeId1, String recipeId2) {
        Map<String, Double> featureVector1 = featureMatrix.get(recipeId1);
        Map<String, Double> featureVector2 = featureMatrix.get(recipeId2);
        if (featureVector1 == null || featureVector2 == null) {
            return 0.0;
        }
        // 计算余弦相似度
        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        for (String feature : featureVector1.keySet()) {
            if (featureVector2.containsKey(feature)) {
                dotProduct += featureVector1.get(feature) * featureVector2.get(feature);
            }
            magnitude1 += Math.pow(featureVector1.get(feature), 2);
        }
        for (String feature : featureVector2.keySet()) {
            magnitude2 += Math.pow(featureVector2.get(feature), 2);
        }
        if (magnitude1 == 0.0 || magnitude2 == 0.0) {
            return 0.0;
        } else {
            double cosineSimilarity = dotProduct / (Math.sqrt(magnitude1) * Math.sqrt(magnitude2));
            // 加入点击率权重
            double clickRateWeight = calculateClickRateWeight(recipeId2);
            // 加入点赞权重
            double likeWeight = calculateLikeWeight(recipeId2);
            // 加入收藏权重
            double collectWeight = calculateCollectWeight(recipeId2);
            // 返回加权后的相似度（再次进行二次加权, 收藏 * 0.6 + 点赞 * 0.3 + 点击 * 0.1）
            return cosineSimilarity * clickRateWeight * likeWeight * collectWeight;
        }
    }
    /**
     计算菜谱的点击率权重
     @param recipeId 菜谱的id
     @return 点击率权重
     */
    private double calculateClickRateWeight(String recipeId) {
        // 假设点击率越高的菜谱越受欢迎，权重越高
        // 获取菜谱的点击率
        double clickRate = clickCount.get(recipeId);
        // 如果点击率为0，直接返回1
        if (clickRate <= 0.0) {
            return 1.0;
        } else {
            // 如果点击率大于0，返回一个在1到2之间的权重值，权重随点击率的增加而增加
            return 1.0 + Math.log(clickRate) * 0.5;
        }
    }

    /**
     计算菜谱的点赞权重
     @param recipeId 菜谱的id
     @return 点赞权重
     */
    private double calculateLikeWeight(String recipeId) {
        // 假设点赞数越多的菜谱越受欢迎，权重越高
        // 获取菜谱的点赞数
        int likeCount = upCount.get(recipeId);
        // 如果点赞数为0，直接返回1
        if (likeCount <= 0) {
            return 1.0;
        } else {
            // 如果点赞数大于0，返回一个在1到2之间的权重值，权重随点赞数的增加而增加
            return 1.0 + Math.log(likeCount) * 0.8;
        }
    }
    /**
     * 计算菜谱收藏权重
     */
    private double calculateCollectWeight(String recipeId) {
        // 假设收藏数越多的菜谱越受欢迎，权重越高
        // 获取菜谱收藏数
        int collectRate = collectCount.get(recipeId);
        // 如果收藏数为0，直接返回1
        if (collectRate <= 0) {
            return 1.0;
        } else {
            // 如果点赞数大于0，返回一个在1到2之间的权重值，权重随收藏数的增加而增加
            return 1.0 + Math.log(collectRate) * 1;
        }
    }

    public List<String> recommendRecipes(String userId) {
        // 读取文件
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("::");
                String dishId = data[0];
                String dishName = data[1];
                List<String> materialNames = List.of(data[2].split(","));
                List<String> tags = List.of(data[3].split("@@"));
                List<String> steps = List.of(data[4].split("&&"));
                addRecipeFeature(dishId, dishName, materialNames, steps, tags);
            }
        } catch (IOException e) {
            log.info("read file error, case: {}, msg: {}", e.getCause(), e.getMessage());
        }
        return recommend(userId);
    }
    /**
     * 菜谱推荐
     *
     * @param userId 用户id
     * @return 推荐结果
     */
    public List<String> recommend(String userId) {
        Object clickObj = redisTemplate.opsForValue().get(DishConstant.DISH_CLICK_NUM);
        clickCount = JSON.parseObject(JSON.toJSONString(clickObj), new TypeReference<>() {
        });
        Object upObj = redisTemplate.opsForValue().get(DishConstant.DISH_UP_NUM);
        upCount = JSON.parseObject(JSON.toJSONString(upObj), new TypeReference<>() {
        });
        Object collectObj = redisTemplate.opsForValue().get(DishConstant.DISH_COLLECT_NUM);
        collectCount = JSON.parseObject(JSON.toJSONString(collectObj), new TypeReference<>() {
        });
        // 获取用户的喜好菜谱
        LambdaQueryWrapper<MemberDishLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MemberDishLog::getMemberId, userId);
        List<MemberDishLog> memberDishLogs = memberDishLogMapper.selectList(wrapper);
        if (ObjectUtils.isNotEmpty(memberDishLogs)) {
            // TODO: 考虑用户喜好该菜谱的标准是什么（当前按照点赞/收藏/点击率>2/日期距离当前的天数必须大于2）
            List<String> likedRecipes = memberDishLogs.stream().filter(item -> {
                long diff = DateUtils.dayDiff(item.getCreateTime());
                return (item.getIsCollect() || item.getIsUp() || item.getClickNum() >= 2) && diff <= 2;
            }).map(MemberDishLog::getDishId).collect(Collectors.toList());
            // 统计所有菜谱的得分
            Map<String, Double> recipeScores = new HashMap<>();
            for (String recipeId : featureMatrix.keySet()) {
                // 如果该菜谱已经被用户喜欢过，则跳过【为了解决随着时间的增长，大量数据被跳过的问题，加入了时间判断，距离当前时间的天数<2才会被过滤】
                if (likedRecipes.contains(recipeId)) {
                    continue;
                }
                double score = 0.0;
                // 计算该菜谱与用户喜欢的所有菜谱的相似度之和
                for (String likedRecipeId : likedRecipes) {
                    double similarity = calculateSimilarity(recipeId, likedRecipeId);
                    score += similarity;
                }
                recipeScores.put(recipeId, score);
            }
            // 根据得分排序，取前10个作为推荐菜谱
            return recipeScores.entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .map(Map.Entry::getKey)
                    .limit(10)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}

