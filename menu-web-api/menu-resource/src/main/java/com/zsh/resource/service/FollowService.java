package com.zsh.resource.service;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Follow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 18179
 * @description 针对表【follow】的数据库操作Service
 * @createDate 2023-03-30 22:18:07
 */
public interface FollowService extends IService<Follow> {

    /**
     * 用户的关注与取消关注
     *
     * @param userId    当前登录的用户id
     * @param publisher 当前登录用户即将关注的用户id
     * @param type      【0 - 取消关注】 【1 - 关注】
     */
    CommonResult<Object> updateFollow(String userId, String publisher, Integer type);

}
