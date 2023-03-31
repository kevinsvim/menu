package com.zsh.resource.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zsh on 2023/3/30
 *
 * @author zsh
 */
@Data
public class CommentVo {
    /**
     * 评论id
     */
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 菜品id
     */
    private Long dishId;

    /**
     * 评论用户id
     */
    private Long memberId;

    /**
     * 评论用户头像
     */
    private String imageUrl;

    /**
     * 评论用户名
     */
    private String username;

    /**
     * 评论时间
     */
    private Date publishDate;
}
