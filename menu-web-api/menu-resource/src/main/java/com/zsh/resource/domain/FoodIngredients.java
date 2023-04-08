package com.zsh.resource.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName food_ingredients
 */
@TableName(value ="food_ingredients")
@Data
public class FoodIngredients implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 食材名
     */
    private String name;

    /**
     * 食材别名
     */
    private String alias;

    /**
     * 热量
     */
    private String caloric;

    /**
     * 适宜人群
     */
    private String suitable;

    /**
     * 食材图片
     */
    private String imageUrl;

    /**
     * 食材介绍
     */
    private String intro;

    /**
     * 营养成分
     */
    private String nutritional;

    /**
     * 功效
     */
    private String effect;

    /**
     * 营养价值
     */
    private String nutritiveValue;

    /**
     * 做法
     */
    private String doing;

    /**
     * 挑选方法
     */
    private String pick;

    /**
     * 存储方法
     */
    private String storage;

    /**
     * 相克食物
     */
    private String restrictionFood;

    /**
     * 禁忌人群
     */
    private String tabooPopulation;

    /**
     * 搭配食物
     */
    private String foodMatching;

    /**
     * 状态字段[1-第一步 2 - 第二部 3 - 第三步 4 - 第四步 5 - 已发布]
     */
    private Integer status;

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