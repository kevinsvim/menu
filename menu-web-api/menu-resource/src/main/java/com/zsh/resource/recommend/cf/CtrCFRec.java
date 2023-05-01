package com.zsh.resource.recommend.cf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.constant.DishConstant;
import com.zsh.resource.mapper.DishMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * Created by zsh on 2023/4/28
 * 点击率预估算法
 * @author zsh
 */
@Component
public class CtrCFRec {

    private final DishMapper dishMapper;
    private final RedisTemplate<Object, Object> redisTemplate;
    public CtrCFRec(DishMapper dishMapper, RedisTemplate<Object, Object> redisTemplate) {
        this.dishMapper = dishMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 1. 获取用户标签
     * 2. 判断用户是否存在标签
     * 3. 不存在，则执行基于热度推荐
     * 4. 存在, 将标签与用户点击的标签拼接
     * 5. 查出标签下的所有数据
     * 6. 过滤出用户已经点击过的
     */
    public CommonResult<Object> recommendDish(String userId) {
        // 获取热度数据
        Object hotScore = redisTemplate.opsForValue().get(DishConstant.HOT_SCORE);
        Map<String, String> hotScoreMap = JSON.parseObject(JSON.toJSONString(hotScore), new TypeReference<>() {
        });

        // 获取用户标签
        String tags = dishMapper.getTags(userId);
        if (StringUtils.isNotEmpty(tags)) {
            // 标签不为空
            List<String> list = List.of(tags.split(","));
            // 获取历史点击数据

        } else {
            // 标签为空

        }

        return CommonResult.success();
    }
}
