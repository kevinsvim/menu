package com.zsh.member.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName member
 */
@Data
@TableName("member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String imageUrl;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 自我介绍
     */
    private String intro;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别(0男1女)
     */
    private Boolean sex;

    /**
     * 经验
     */
    private Integer exp;

    /**
     * 访问量
     */
    private Long vists;


}