package com.zsh.resource.recommend.cf;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsh.resource.constant.DishConstant;
import com.zsh.resource.domain.Follow;
import com.zsh.resource.mapper.FollowMapper;
import com.zsh.resource.service.FollowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by zsh on 2023/4/10
 * 本周用户推荐
 * 参数指标 1. 上被粉丝关注的数量 2. 上周发布菜谱的平均评分【点赞 + 浏览量 + 收藏】
 * @author zsh
 */
@Component
public class MemberHotRec {

    private final FollowMapper followMapper;
    private final RedisTemplate<Object, Object> redisTemplate;
    public MemberHotRec(FollowMapper followMapper, RedisTemplate<Object, Object> redisTemplate) {
        this.followMapper = followMapper;
        this.redisTemplate = redisTemplate;
    }
    /**
     *
     * 1. 加载粉丝表数据
     * 2. 上周发布的菜谱平均评分
     * 权重 4 : 6
     */
    public void memberRecommend() {
        //TODO:待写
        // 1.查询出所有用户
        // 2.查询粉丝表, 查询出上周每个用户被关注的数量
        // 3.查询出上周所有用户发布的菜谱【】
        // 4.分析每个菜谱的访问量、点赞量、收藏,对其做平均评分
        // 5.再以每个用户为基准，如果该用户在上周发布的菜谱数 >= 2，则去掉其最小值计算该用户最终上周得分
        // 6.如果发布的菜谱数 >= 5,则去掉两个最差评分，去最好的计算该用户上周的最终得分
        List<String> memberIds = followMapper.getAllMember();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < memberIds.size(); i++) {
            map.put(memberIds.get(i), 0);
        }
        List<Follow> follows = followMapper.selectList(null);
        follows.forEach(item -> {
            map.replace(item.getMemberId(), map.get(item.getMemberId()) + 1);
        });
        Map<String, Integer> hotSortedMap = sortDescend(map);
        List<String> finalIds = new ArrayList<>(8);
        int k = 0;
        for (String key : hotSortedMap.keySet()) {
            ++k;
            if (k > 6) {
                break;
            }
            finalIds.add(key);
        }
        redisTemplate.opsForValue().set(DishConstant.MEMBER_REC, finalIds, 7, TimeUnit.DAYS);
    }

    /**
     * 对map集合的value进行降序排列方法
     * @param map 需要排序的map集合
     * @param <K> 键
     * @param <V> 值
     */
    public <K, V extends Comparable<? super V>> Map<K, V> sortDescend(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            int compare = (o1.getValue()).compareTo(o2.getValue());
            return -compare;
        });
        Map<K, V> returnMap = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry:list) {
            returnMap.put(entry.getKey(), entry.getValue());
        }
        return returnMap;
    }
}
