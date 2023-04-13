package com.zsh.resource.service;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.MemberNoteLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 18179
* @description 针对表【note_log】的数据库操作Service
* @createDate 2023-04-03 16:22:36
*/
public interface NoteLogService extends IService<MemberNoteLog> {

    CommonResult<Object> updateUp(String userId, String dishId, Integer type);

    CommonResult<Object> updateCollect(Integer type, String dishId, String memberId);
}
