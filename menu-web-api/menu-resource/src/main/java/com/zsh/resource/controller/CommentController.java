package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.CommentService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zsh on 2023/3/31
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/comment")
public class CommentController {

    public final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 添加一条评论
     * @param userId 评论用户id
     * @param content 评论内容
     */
    @PostMapping("/addComment")
    public CommonResult<Object> addComment(@RequestHeader("userId") String userId,
                                           @RequestParam("content") String content,
                                           @RequestParam("dishId") String dishId) {
        return commentService.saveComment(userId, content, dishId);
    }

    /**
     * 删除一条评论
     * @param commentId 评论id
     */
    @DeleteMapping("/deleteCommentById/{commentId}")
    public CommonResult<Object> deleteCommentById(@PathVariable("commentId") String commentId) {
        commentService.removeById(commentId);
        return CommonResult.success();
    }
}
