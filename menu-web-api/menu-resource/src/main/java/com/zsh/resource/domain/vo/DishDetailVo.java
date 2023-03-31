package com.zsh.resource.domain.vo;

import com.zsh.resource.domain.Material;
import com.zsh.resource.domain.Step;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsh on 2023/3/29
 * 菜谱详情页VO
 * 放入缓存,实现序列化接口
 * @author zsh
 */
@Data
public class DishDetailVo implements Serializable {

    private static final long serialVersionUID = 2L;

    /**
     * 菜谱id
     */
    private String id;

    /**
     * 菜谱图片url
     */
    private String imageUrl;

    /**
     * 菜谱名
     */
    private String name;

    /**
     * 是否独家
     */
    private Boolean isSole;

    /**
     * 点赞量
     */
    private Long upNum;

    /**
     * 收藏量
     */
    private Long collectNum;

    /**
     * 当前用户是否关注该菜谱发布者
     */
    private Boolean isFollow;

    /**
     * 当前用户是否已经收藏该菜谱
     */
    private Boolean isCollect;

    /**
     * 菜谱描述
     */
    private String description;

    /**
     * 菜谱发布者用户名
     */
    private String username;

    /**
     * 菜谱发布者用户id
     */
    private Long userId;

    /**
     * 菜谱发布者用户头像
     */
    private String avatar;

    /**
     * 菜谱用料
     */
    private List<Material> materials;

    /**
     * 菜谱用料名集合
     */
    private String materialName;

    /**
     * 菜谱用料用量集合
     */
    private String dosage;

    /**
     * 步骤
     */
    private List<Step> steps;

    /**
     * 烹饪技巧
     */
    private String skill;

    /**
     * 当前用户是否已经点赞
     */
    private Boolean isUp;

    /**
     * 评论
     */
    private List<CommentVo> comments;
}
