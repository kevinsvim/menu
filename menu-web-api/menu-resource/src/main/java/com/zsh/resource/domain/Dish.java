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
    private Long id;

    /**
     * 菜品图片url
     */
    private String imageUrl;

    /**
     * 食材id
     */
    private Long materialId;

    /**
     * 制作难度等级id
     */
    private Long productionLevelId;

    /**
     * 制作时间id
     */
    private Long productionTimeId;

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
    private Long typeId;

    /**
     * 工艺id
     */
    private Long cookTechnologyId;

    /**
     * 品味id
     */
    private Long flavorId;

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
    private Long publisherId;

    /**
     * 是否是独家发布
     */
    private boolean isSole;

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