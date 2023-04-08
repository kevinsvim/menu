package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.NoteCommentService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zsh on 2023/4/3
 * 笔记评论控制器
 * @author zsh
 */
@RestController
@RequestMapping("/resource/noteComment")
public class NoteCommentController {
    public final NoteCommentService noteCommentService;
    public NoteCommentController(NoteCommentService noteCommentService) {
        this.noteCommentService = noteCommentService;
    }

    /**
     * 添加一条笔记评论
     * @param userId 评论用户id
     * @param content 评论内容
     */
    @PostMapping("/addComment")
    public CommonResult<Object> addComment(@RequestHeader("userId") String userId,
                                           @RequestParam("content") String content,
                                           @RequestParam("noteId") String noteId) {
        return noteCommentService.saveComment(userId, content, noteId);
    }

    /**
     * 删除一条评论
     * @param commentId 评论id
     */
    @DeleteMapping("/deleteCommentById/{commentId}")
    public CommonResult<Object> deleteCommentById(@PathVariable("commentId") String commentId) {
        noteCommentService.removeById(commentId);
        return CommonResult.success();
    }
}
