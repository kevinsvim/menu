package com.zsh.resource.domain.vo;

import lombok.Data;

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
    private Long id;

    /**
     * 菜谱名
     */
    private String name;

    /**
     * 用户名
     */
    private String username;

    /**
     * 图片链接
     */
    private String imageUrl;
}
