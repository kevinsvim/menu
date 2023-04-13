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
 * @TableName note_log
 */
@TableName(value ="member_note_log")
@Data
public class MemberNoteLog implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * 笔记id
     */
    private String noteId;

    /**
     * 用户id
     */
    private String memberId;

    /**
     * 点击数量
     */
    private Integer clickNum;

    /**
     * 是否点赞
     */
    private Boolean isUp;

    /**
     * 是否收藏
     */
    private Boolean isCollect;

    /**
     * 评论数量
     */
    private Integer commentNum;

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