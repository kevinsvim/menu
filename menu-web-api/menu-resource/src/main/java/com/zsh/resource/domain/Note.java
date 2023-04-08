package com.zsh.resource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName note
 */
@TableName(value ="note")
@Data
public class Note implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 图集
     */
    private String imageUrls;

    /**
     * 标题
     */
    private String title;

    /**
     * 介绍
     */
    private String intro;

    /**
     * 发布人
     */
    private Long memberId;

    /**
     * 日记状态【1 - 未审核  2 - 已审核】
     */
    private Integer state;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}