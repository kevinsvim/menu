package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.constant.CommentEnum;
import com.zsh.resource.domain.Comment;
import com.zsh.resource.domain.NoteComment;
import com.zsh.resource.service.NoteCommentService;
import com.zsh.resource.mapper.NoteCommentMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author 18179
* @description 针对表【note_comment】的数据库操作Service实现
* @createDate 2023-04-03 11:39:28
*/
@Service
public class NoteCommentServiceImpl extends ServiceImpl<NoteCommentMapper, NoteComment>
    implements NoteCommentService{

    @Override
    public CommonResult<Object> saveComment(String userId, String content, String noteId) {

        NoteComment comment = new NoteComment();
        comment.setNoteId(Long.valueOf(noteId));
        comment.setContent(content);
        comment.setMemberId(Long.valueOf(userId));
        comment.setPublishDate(new Date());
        comment.setStatus(CommentEnum.UNCHECKED.getIndex());
        this.save(comment);
        return CommonResult.success();
    }
}




