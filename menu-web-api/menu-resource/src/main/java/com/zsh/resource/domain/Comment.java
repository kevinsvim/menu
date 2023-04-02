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
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 菜品id
     */
    private String dishId;

    /**
     * 评论用户id
     */
    private String memberId;


    /**
     * 评论时间
     */
    private Date publishDate;

    /**
     * 评论状态【1 - 未审核 2 - 已审核】
     */
    private Integer status;

    /**
     * 
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