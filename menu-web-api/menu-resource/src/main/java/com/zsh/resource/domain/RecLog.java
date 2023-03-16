package com.zsh.resource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName rec_log
 */
@TableName(value ="rec_log")
@Data
public class RecLog implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
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
     * 点击数量
     */
    private Integer clickNum;

    /**
     * 是否收藏
     */
    private Boolean isCollect;

    /**
     * 是否点赞
     */
    private Boolean isUp;

    /**
     * 评论数量
     */
    private Integer commentNum;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}