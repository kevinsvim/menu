package com.zsh.resource.domain.vo;

import lombok.Data;

/**
 * Created by zsh on 2023/4/1
 * 某个分类下的全部菜谱
 * @author zsh
 */
@Data
public class DishCategoryVo {
    /**
     * 菜谱id
     */
    private String id;

    /**
     * 图片链接
     */
    private String imageUrl;
    /**
     * 菜谱名
     */
    private String name;

    /**
     * 收藏量
     */
    private Integer collectNum;

    /**
     * 点赞量
     */
    private Integer upNum;

    /**
     * 点击量
     */
    private Integer clickNum;

    /**
     * 发布者id
     */
    private String publisherId;

    /**
     * 发布者用户名
     */
    private String username;

    /**
     * 发布者头像
     */
    private String avatar;
}
