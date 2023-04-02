package com.zsh.resource.recommend.cf;

import com.zsh.resource.constant.DishConstant;
import com.zsh.resource.domain.RecLog;
import com.zsh.resource.mapper.RecLogMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by zsh on 2023/3/13
 * 基于热度值算法推荐
 *  S = 0.3 x comment + 0.35 x collect + 0.15 x up + 0.15 x click - 0.1 x days
 * @author zsh
 */
@Component
public class MenuHotValueCFRec {

    private final RecLogMapper recLogMapper;
    private final RedisTemplate<Object, Object> redisTemplate;

    public MenuHotValueCFRec(RecLogMapper recLogMapper, RedisTemplate<Object, Object> redisTemplate) {
        this.recLogMapper = recLogMapper;
        this.redisTemplate = redisTemplate;
    }
    /**
     *  1. 加载日志数据
     *  2. 计算热度值
     *      计算规则: S = 0.3 x comment + 0.35 x collect + 0.15 x up + 0.15 x click - 0.1 * days
     *  4. 对热度值进行排序
     *  5. 取出前一百的菜品id放入redis
     *  6. 定时器 -- 每隔一天进行更新热度值
     */
    public void recommendHotRec() {
        // 加载所有数据
        List<RecLog> items = recLogMapper.getAllLogData();
        // 计算热度值
        Map<String, Double> dishScoreMap = new HashMap<>();
        Map<String, Integer> calTimeMap = new HashMap<>();
        items.forEach(item -> {
            String dishId = item.getDishId();
            Integer clickNum = item.getClickNum();
            Integer commentNum = item.getCommentNum();
            Boolean isCollect = item.getIsCollect();
            Boolean isUp = item.getIsUp();
            LocalDateTime createTime = item.getCreateTime();
            LocalDateTime currentTime = LocalDateTime.now();
            // 计算时间差(天数)
            long days = Duration.between(createTime, currentTime).toDays();
            // 计算当前条数据的分数
            double score = 0.15 * (clickNum > 10 ? 10 : clickNum)
                    + 0.30 * (commentNum > 10 ? 10 : commentNum)
                    + 0.35 * (isCollect ? 10 : 0)
                    + 0.15 * (isUp ? 10 : 0)
                    - 0.10 * days;
            // 保存dishId : score
            if (dishScoreMap.containsKey(dishId)) {
                // 将map中value进行相加
                dishScoreMap.compute(dishId, (k, v) -> v == null ? 0 : v + score);
            } else {
                dishScoreMap.put(dishId, score);
            }
            if (calTimeMap.containsKey(dishId)) {
                // 次数加1
                calTimeMap.compute(dishId, (k, v) -> v == null ? 0 : v+1);
            } else {
                calTimeMap.put(dishId, 0);
            }
        });
        // 对热度值进行取平均值
        for (Map.Entry<String, Double> entry : dishScoreMap.entrySet()) {
            double value = entry.getValue() / (calTimeMap.get(entry.getKey()) == 0 ? 1 : calTimeMap.get(entry.getKey()));
            value = value < 1 ? 1 : value;
            entry.setValue(value);
        }
        // 对热度值进行降序排列
        Map<String, Double> hotScoreMap = sortDescend(dishScoreMap);
        // 将排序后的结果放入redis
        redisTemplate.opsForValue().set(DishConstant.HOT_SCORE, hotScoreMap, 2, TimeUnit.DAYS);
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
