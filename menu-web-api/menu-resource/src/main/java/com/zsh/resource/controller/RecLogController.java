package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.RecLogService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zsh on 2023/3/31
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/recLog")
public class RecLogController {

    private final RecLogService recLogService;
    public RecLogController(RecLogService recLogService) {
        this.recLogService = recLogService;
    }
    /**
     *  是否取消收藏 【0 - 取消收藏】 【1 - 收藏】
     */
    @PatchMapping("/collect")
    public CommonResult<Object> collect(@RequestParam("type") Integer type, @RequestParam("dishId") String dishId,
                                        @RequestParam("memberId") String memberId) {
        return recLogService.updateCollect(type, dishId, memberId);
    }
    /**
     * 是否取消点赞 【0 - 取消点赞】 【1 - 点赞】
     */
    @PatchMapping("/up")
    public CommonResult<Object> up(@RequestHeader("userId") String userId, @RequestParam("dishId") String dishId,
                                   @RequestParam("type") Integer type) {
        return recLogService.updateUp(userId, dishId, type);
    }
}
