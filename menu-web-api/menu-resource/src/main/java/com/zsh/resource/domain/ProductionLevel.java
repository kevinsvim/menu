package com.zsh.resource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import lombok.Data;

/**
 * 
 * @TableName production_level
 */
@TableName(value ="production_level")
@Data
public class ProductionLevel implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 难度名
     */
    private String levelName;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}