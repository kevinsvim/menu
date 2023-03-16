package com.zsh.resource.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.constant.DishConstant;
import com.zsh.resource.domain.Dish;
import com.zsh.resource.service.DishService;
import com.zsh.resource.mapper.DishMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author 18179
* @description 针对表【dish】的数据库操作Service实现
* @createDate 2023-03-16 22:32:49
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService{

    private final RedisTemplate<Object, Object> redisTemplate;
    public DishServiceImpl(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Override
    public CommonResult<Object> getHotRec() {
        Object hotScore = redisTemplate.opsForValue().get(DishConstant.HOT_SCORE);
        Map<String, String> hotScoreMap = JSON.parseObject(JSON.toJSONString(hotScore), new TypeReference<Map<String, String>>() {
        });
        return CommonResult.success(hotScoreMap);
    }
}




