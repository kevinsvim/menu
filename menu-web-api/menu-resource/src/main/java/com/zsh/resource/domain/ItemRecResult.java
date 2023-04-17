package com.zsh.resource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName item_rec_result
 */
@TableName(value ="item_rec_result")
@Data
public class ItemRecResult implements Serializable {
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
    private Double score;

    /**
     * 
     */
    private Long memberId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}