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
    private Long id;

    /**
     * 
     */
    private Long dishId;

    /**
     * 
     */
    private Long memberId;

    /**
     * 
     */
    private Integer clickNum;

    /**
     * 
     */
    private Integer isCollect;

    /**
     * 
     */
    private Integer isUp;

    /**
     * 
     */
    private Integer commentNum;

    /**
     * 是否删除
     */
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