package com.zsh.resource.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName article_theme
 */
@TableName(value ="article_theme")
@Data
public class ArticleTheme implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 主题标题
     */
    private String title;

    /**
     * 主题描述
     */
    private String description;

    /**
     * 主题图片
     */
    private String imageUrl;

    /**
     * 关联文章的数量
     */
    private Integer articleNum;

    /**
     * 
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}