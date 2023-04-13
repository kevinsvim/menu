package com.zsh.resource.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName note_rec_log
 */
@TableName(value ="note_rec_log")
@Data
public class NoteRecLog implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private Integer upNum;

    /**
     * 
     */
    private Integer collectNum;

    /**
     * 
     */
    private Integer clickNum;

    /**
     * 
     */
    private Long noteId;

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