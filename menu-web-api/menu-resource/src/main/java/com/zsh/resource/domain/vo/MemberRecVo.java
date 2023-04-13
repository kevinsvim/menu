package com.zsh.resource.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zsh on 2023/4/10
 * 首页推荐用户信息
 * @author zsh
 */
@Data
public class MemberRecVo implements Serializable {

    private String id;
    private String imageUrl;
    private String nickName;
    private String username;
    private int fansNum;
}
