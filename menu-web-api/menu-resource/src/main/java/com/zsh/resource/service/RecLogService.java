package com.zsh.resource.service;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.RecLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 18179
* @description 针对表【rec_log】的数据库操作Service
* @createDate 2023-03-13 12:06:08
*/
public interface RecLogService extends IService<RecLog> {

    /**
     * 修改当前用户的收藏与否
     */
    CommonResult<Object> updateCollect(Integer type, String dishId, String memberId);

    /**
     * 是否取消点赞 【0 - 取消点赞】 【1 - 点赞】
     */
    CommonResult<Object> updateUp(String userId, String dishId, Integer type);
}
