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
 * @TableName step
 */
@TableName(value ="step")
@Data
public class Step implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 菜品id
     */
    private String dishId;

    /**
     * 步骤图片url
     */
    private String imageUrl;

    /**
     * 步骤内容
     */
    private String content;

    /**
     * 所属第几步
     */
    private Integer rank;

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