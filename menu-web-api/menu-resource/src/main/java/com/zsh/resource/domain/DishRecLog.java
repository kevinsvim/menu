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
 * @TableName dish_rec_log
 */
@TableName(value ="dish_rec_log")
@Data
public class DishRecLog implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 点赞总量
     */
    private Integer upNum;

    /**
     * 收藏总量
     */
    private Integer collectNum;

    /**
     * 点击总量
     */
    private Integer clickNum;

    /**
     * 菜谱id
     */
    private Long dishId;

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