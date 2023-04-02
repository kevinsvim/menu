package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.constant.CommentEnum;
import com.zsh.resource.domain.Comment;
import com.zsh.resource.service.CommentService;
import com.zsh.resource.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author 18179
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-03-29 17:55:17
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    /**
     * 保存一条评论
     * 状态是未审核 【1 - 未审核 2 - 已审核】
     */
    @Override
    public CommonResult<Object> saveComment(String userId, String content, String dishId) {
        Comment comment = new Comment();
        comment.setDishId(dishId);
        comment.setContent(content);
        comment.setMemberId(userId);
        comment.setPublishDate(new Date());
        comment.setStatus(CommentEnum.UNCHECKED.getIndex());
        this.save(comment);
        return CommonResult.success();
    }
}




