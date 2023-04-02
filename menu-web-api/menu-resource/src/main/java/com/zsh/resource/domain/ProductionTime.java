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
 * @TableName production_time
 */
@TableName(value ="production_time")
@Data
public class ProductionTime implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 制作难度等级名
     */
    private String timeName;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}