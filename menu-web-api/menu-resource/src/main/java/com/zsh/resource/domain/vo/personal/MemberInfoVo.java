package com.zsh.resource.domain.vo.personal;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zsh on 2023/4/10
 *
 * @author zsh
 */
@Data
public class MemberInfoVo implements Serializable {
    private String id;
    private String nickname;
    private String username;
    private String imageUrl;
    private String intro;
    private int sex;
    private String address;
    private Long exp;
    private Integer fans;
    private Integer follows;
}
