package com.zsh.resource.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zsh on 2023/3/28
 * 获取精选菜谱VO
 * @author zsh
 */
@Data
public class DishConcentrationVo {
    /**
     * 菜谱id
     */
    private String id;

    /**
     * 发布时间
     */
    private Date publishDate;

    /**
     * 审核员评价
     */
    private String evaluate;

    /**
     * 菜谱名
     */
    private String name;

    /**
     * 发布者用户名
     */
    private String username;

    /**
     * 图片链接
     */
    private String imageUrl;

    /**
     * 浏览量
     */
    private int views;

    /**
     * 点赞量
     */
    private int upNum;

    /**
     * 收藏量
     */
    private int collectNum;

    /**
     * 评论量
     */
    private int commentNum;

}
