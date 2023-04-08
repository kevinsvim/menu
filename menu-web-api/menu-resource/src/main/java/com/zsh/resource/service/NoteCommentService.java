package com.zsh.resource.service;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.NoteComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 18179
* @description 针对表【note_comment】的数据库操作Service
* @createDate 2023-04-03 11:39:28
*/
public interface NoteCommentService extends IService<NoteComment> {

    CommonResult<Object> saveComment(String userId, String content, String noteId);
}
