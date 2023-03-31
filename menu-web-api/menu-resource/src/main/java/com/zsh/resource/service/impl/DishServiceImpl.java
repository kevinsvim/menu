package com.zsh.resource.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.constant.DishConstant;
import com.zsh.resource.domain.*;
import com.zsh.resource.domain.dto.PublishDishDto;
import com.zsh.resource.domain.vo.DishConcentrationVo;
import com.zsh.resource.domain.vo.DishDetailVo;
import com.zsh.resource.mapper.CommentMapper;
import com.zsh.resource.mapper.FollowMapper;
import com.zsh.resource.mapper.RecLogMapper;
import com.zsh.resource.service.DishService;
import com.zsh.resource.mapper.DishMapper;
import com.zsh.resource.service.MaterialService;
import com.zsh.resource.service.StepService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 18179
 * @description 针对表【dish】的数据库操作Service实现
 * @createDate 2023-03-16 22:32:49
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    private final MaterialService materialService;
    private final StepService stepService;
    private final DishMapper dishMapper;
    private final RedisTemplate<Object, Object> redisTemplate;
    private final CommentMapper commentMapper;
    private final RecLogMapper recLogMapper;
    private final FollowMapper followMapper;

    public DishServiceImpl(RedisTemplate<Object, Object> redisTemplate, MaterialService materialService,
                           StepService stepService, DishMapper dishMapper,
                           CommentMapper commentMapper, RecLogMapper recLogMapper,
                           FollowMapper followMapper) {
        this.redisTemplate = redisTemplate;
        this.materialService = materialService;
        this.stepService = stepService;
        this.dishMapper = dishMapper;
        this.commentMapper = commentMapper;
        this.recLogMapper = recLogMapper;
        this.followMapper = followMapper;
    }

    /**
     * 获取热度值推荐的数据
     */
    @Override
    public CommonResult<Object> getHotRec() {
        Object hotScore = redisTemplate.opsForValue().get(DishConstant.HOT_SCORE);
        Map<String, String> hotScoreMap = JSON.parseObject(JSON.toJSONString(hotScore), new TypeReference<>() {
        });
        // 根据所有的id查询出所有相关的菜品数据
        Set<String> dishIds = hotScoreMap.keySet();

        // 遍历每一个dishId,从hotScoreMap获取进行菜品排序
        return CommonResult.success(hotScoreMap);
    }

    /**
     * 保存发布的菜谱数据
     *
     * @param publishDishDto 需要保存的数据
     */
    // 开启事务
    @Transactional
    @Override
    public CommonResult<Object> saveDish(PublishDishDto publishDishDto) {
        // 1.数据拷贝到Dish
        Dish dish = new Dish();
        BeanUtils.copyProperties(publishDishDto, dish);
        // 2.保存材料数据
        StringBuilder materialNamesAppend = new StringBuilder();
        StringBuilder dosagesAppend = new StringBuilder();
        List<Material> materials = publishDishDto.getMaterialMenu();
        materials.forEach(material -> {
            materialNamesAppend.append(material.getName()).append(",");
            dosagesAppend.append(material.getDosage()).append(",");
        });
        String materialNames = materialNamesAppend.substring(0, materialNamesAppend.length() - 1);
        String dosages = dosagesAppend.substring(0, dosagesAppend.length() - 1);
        // 保存食材
        Material material = new Material();
        material.setName(materialNames);
        material.setDosage(dosages);
        materialService.save(material);
        // 3.保存菜谱
        dish.setSole(publishDishDto.getIsSole());
        dish.setStatus(0);
        dish.setMaterialId(material.getId());
        this.save(dish);

        // 4.保存Step
        List<Step> steps = publishDishDto.getStepMenu();
        steps.forEach(x -> {
            x.setDishId(dish.getId());
        });
        stepService.saveBatch(steps);

        return CommonResult.success();
    }

    /**
     * 获取热度值推荐的内容
     */
    @Override
    public List<DishConcentrationVo> getConcentrationMenu() {
        // 1.获取缓存中推荐的菜谱
        Object hotScore = redisTemplate.opsForValue().get(DishConstant.HOT_SCORE);
        Map<String, String> hotScoreMap = JSON.parseObject(JSON.toJSONString(hotScore), new TypeReference<>() {
        });
        if (hotScore != null) {
            Set<String> dishIds = hotScoreMap.keySet();
            // 2.取出前8个菜谱id
            List<String> ids = dishIds.stream().limit(8).collect(Collectors.toList());
            // 3.根据id集合查询相关信息
            return dishMapper.getConcentrationRecContent(ids);
        }
        return null;
    }

    /**
     * 获取菜谱页详情
     * @param id 菜谱id
     * @param userId 《---- 当前登录的用户id ---》
     */
//    @Cacheable(value = "dishDetail", key = "#id + #userId")
    @Override
    public DishDetailVo getDishDetail(String id, Long userId) {
        // 分为用户登录和未登录两种情况
        // 查询菜谱相关数据
        Long dishId = Long.valueOf(id);
        DishDetailVo dishDetail = dishMapper.getDishDetail(dishId);
        dishDetail.setIsUp(false);
        dishDetail.setIsCollect(false);
        // 去日志查询该菜谱点赞、收藏数量
        LambdaQueryWrapper<RecLog> recLogWrapper = new LambdaQueryWrapper<>();
        recLogWrapper.eq(RecLog::getDishId, id);
        List<RecLog> recLogs = recLogMapper.selectList(recLogWrapper);
        // 对点赞与收藏进行求和
        long upNum = 0, collectNum = 0;
        for (RecLog recLog : recLogs) {
            upNum += recLog.getIsUp() ? 1 : 0;
            collectNum += recLog.getIsCollect() ? 1 : 0;
            // 判断当前用户是否已经点赞、收藏该菜谱 [前提是userId不能为空,为空则表示该用户还未登录]
            if (ObjectUtils.isNotEmpty(userId) && recLog.getMemberId().equals(userId)) {
                dishDetail.setIsCollect(recLog.getIsCollect());
                dishDetail.setIsUp(recLog.getIsUp());
            }
        }
        dishDetail.setUpNum(upNum);
        dishDetail.setCollectNum(collectNum);
        // 处理菜谱用料名/菜谱用料用量
        List<Material> materials = new ArrayList<>();
        String[] materialNames = dishDetail.getMaterialName().split(",");
        String[] dosages = dishDetail.getDosage().split(",");
        for (int i = 0; i < materialNames.length; i++) {
            Material material = new Material();
            material.setName(materialNames[i]);
            material.setDosage(dosages[i]);
            materials.add(material);
        }
        dishDetail.setMaterials(materials);
        // 查询评论数据
        dishDetail.setComments(commentMapper.getCommentsByDishId(dishId));
        // 查看步骤相关数据
        LambdaQueryWrapper<Step> stepWrapper = new LambdaQueryWrapper<>();
        stepWrapper.eq(Step::getDishId, dishId);
        List<Step> steps = stepService.list(stepWrapper);
        dishDetail.setSteps(steps);
        dishDetail.setIsFollow(false);
        // 查看该用户是否已经关注该菜谱发布者用户
        if (ObjectUtils.isNotEmpty(userId)) {
            LambdaQueryWrapper<Follow> followWrapper = new LambdaQueryWrapper<>();
            followWrapper.eq(Follow::getMemberId, dishDetail.getUserId());
            followWrapper.eq(Follow::getFansId, userId);
            Integer count = followMapper.selectCount(followWrapper);
            dishDetail.setIsFollow(count > 0);
        }
        return dishDetail;
    }
}




