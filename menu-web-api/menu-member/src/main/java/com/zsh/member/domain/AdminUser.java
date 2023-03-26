package com.zsh.member.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName admin_user
 */
@TableName(value ="admin_user")
@Data
public class AdminUser implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 管理员用户名
     */
    private String username;

    /**
     * 管理员用户密码
     */
    private String password;

    /**
     * 管理员用户手机号
     */
    private String phone;

    /**
     * 管理员用户邮箱
     */
    private String email;

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