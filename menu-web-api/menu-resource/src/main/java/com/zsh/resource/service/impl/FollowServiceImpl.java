package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Follow;
import com.zsh.resource.service.FollowService;
import com.zsh.resource.mapper.FollowMapper;
import org.springframework.stereotype.Service;

/**
 * @author 18179
 * @description 针对表【follow】的数据库操作Service实现
 * @createDate 2023-03-30 22:18:07
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow>
        implements FollowService {

    /**
     * 用户的关注与取消关注
     *
     * @param userId    当前登录的用户id
     * @param publisher 当前登录用户即将关注的用户id
     * @param type      【0 - 取消关注】 【1 - 关注】
     */
    @Override
    public CommonResult<Object> updateFollow(String userId, String publisher, Integer type) {
        if (type == 0) {
            // 取消关注
            LambdaQueryWrapper<Follow> cancelWrapper = new LambdaQueryWrapper<>();
            cancelWrapper.eq(Follow::getMemberId, publisher).eq(Follow::getFansId, userId);
            this.remove(cancelWrapper);
        } else if (type == 1) {
            Follow follow = new Follow();
            follow.setFansId(userId);
            follow.setMemberId(publisher);
            this.save(follow);
        } else {
            CommonResult.fail("无效的关注类型!");
        }
        return CommonResult.success();
    }
}




