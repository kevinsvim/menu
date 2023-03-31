package com.zsh.member.domain.vo;

import lombok.Data;

/**
 * Created by zsh on 2023/3/29
 *
 * @author zsh
 */
@Data
public class MemberLoginVo {
    /**
     * 用户id
     */
    private String id;

    /**
     * 用户token
     */
    private String token;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String image_url;
}
