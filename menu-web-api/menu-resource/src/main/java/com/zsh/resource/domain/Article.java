package com.zsh.resource.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 主题id
     */
    private String themeId;

    /**
     * 背景图
     */
    private String imageUrl;

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

    /**
     * 文章内容
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}