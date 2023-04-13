package com.zsh.resource.domain.vo.personal;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zsh on 2023/4/9
 *
 * @author zsh
 */
@Data
public class PersonalDishVo implements Serializable {

    /**
     * 菜谱id
     */
    private String id;

    /**
     * 菜谱图片
     */
    private String imageUrl;

    /**
     * 菜谱名
     */
    private String name;

    /**
     * 发布人id
     */
    private String publisherId;

    /**
     * 访问量
     */
    private Integer viewNum;

    /**
     * 收藏量
     */
    private Integer collectNum;

    /**
     * 当前用户是否收藏[只有当用户登录时,此信息才会被查询]
     */
    private Boolean isCollect;
}
