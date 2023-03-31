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
    CommonResult<Object> updateCollect(Integer type, Long dishId, Long memberId);
}
