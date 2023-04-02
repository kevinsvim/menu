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
 * @TableName follow
 */
@TableName(value ="follow")
@Data
public class Follow implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 粉丝id
     */
    private String fansId;

    /**
     * 被关注者用户id
     */
    private String memberId;

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