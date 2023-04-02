package com.zsh.resource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName dish
 */
@TableName(value ="dish")
@Data
public class Dish implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 菜品图片url
     */
    private String imageUrl;

    /**
     * 食材id
     */
    private String materialId;

    /**
     * 制作难度等级id
     */
    private String productionLevelId;

    /**
     * 制作时间id
     */
    private String productionTimeId;

    /**
     * 是否原创
     */
    private Boolean isOriginal;

    /**
     * 菜品描述
     */
    private String description;

    /**
     * 类型id
     */
    private String typeId;

    /**
     * 工艺id
     */
    private String cookTechnologyId;

    /**
     * 品味id
     */
    private String flavorId;

    /**
     * 小妙招
     */
    private String skill;

    /**
     * 菜品名
     */
    private String name;

    /**
     * 发布者id
     */
    private String publisherId;

    /**
     * 是否是独家发布
     */
    private boolean isSole;

    /**
     * 发布状态
     */
    private Integer status;
    /**
     * 
     */
    private Boolean isDeleted;

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