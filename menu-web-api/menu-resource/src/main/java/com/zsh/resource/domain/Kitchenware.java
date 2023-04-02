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
 * @TableName kitchenware
 */
@TableName(value ="kitchenware")
@Data
public class Kitchenware implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 厨具名
     */
    private String name;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}