package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.FollowService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zsh on 2023/3/31
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/follow")
public class FollowController {

    private final FollowService followService;
    public FollowController(FollowService followService) {
        this.followService = followService;
    }
    /**
     * 关注与取消关注
     */
    @PatchMapping("/updateFollow")
    public CommonResult<Object> follow(@RequestHeader("userId") String userId, @RequestParam("publisher") String publisher,
                                       @RequestParam("type") Integer type) {
        return followService.updateFollow(userId, publisher, type);
    }
}
