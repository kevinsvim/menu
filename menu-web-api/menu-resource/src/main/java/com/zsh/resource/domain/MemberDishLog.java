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
 * @TableName member_dish_log
 */
@TableName(value ="member_dish_log")
@Data
public class MemberDishLog implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String dishId;

    /**
     * 
     */
    private String memberId;

    /**
     * 
     */
    private Integer clickNum;

    /**
     * 
     */
    private Boolean isCollect;

    /**
     * 
     */
    private Boolean isUp;

    /**
     * 默认评分
     */
    private Double initScore;

    /**
     * 
     */
    private Integer commentNum;

    /**
     * 是否删除
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