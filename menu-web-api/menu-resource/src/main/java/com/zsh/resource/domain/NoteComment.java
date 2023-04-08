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
 * @TableName note_comment
 */
@TableName(value ="note_comment")
@Data
public class NoteComment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日记评论内容
     */
    private String content;

    /**
     * 日记id
     */
    private Long noteId;

    /**
     * 评论用户id
     */
    private Long memberId;

    /**
     * 发布日期
     */
    private Date publishDate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer isDeleted;

    /**
     * 
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}