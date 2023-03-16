package com.zsh.msm.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.msm.service.MsmService;
import com.zsh.msm.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by zsh on 2023/2/26
 *
 * @author zsh
 */
@RestController
@RequestMapping("/msm")
public class MsmController {

    private final MsmService msmService;

    private final RedisTemplate<String, String> redisTemplate;

    public MsmController(RedisTemplate<String, String> redisTemplate, MsmService msmService) {
        this.redisTemplate = redisTemplate;
        this.msmService = msmService;
    }
    @GetMapping("/send/{phone}")
    public CommonResult<Boolean> send(@PathVariable String phone) {
        String s = redisTemplate.opsForValue().get(phone);
        if (StringUtils.isNotEmpty(s)) {
            return CommonResult.success();
        }
        String code = RandomUtil.getSixBitRandom();
        boolean send = msmService.send(phone, code);
        if (send) {
            redisTemplate.opsForValue().set(phone, code,5, TimeUnit.MINUTES);
            return CommonResult.success(true);
        }
        return CommonResult.fail(false);
    }
}
