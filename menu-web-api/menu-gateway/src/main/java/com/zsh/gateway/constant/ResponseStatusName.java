package com.zsh.gateway.constant;

import lombok.Getter;

/**
 * Created by zsh on 2023/3/7
 *
 * @author zsh
 */
@Getter
public enum ResponseStatusName {
    SUCCESS("success"),
    CODE("code"),
    DATA("data");

    public String name;

    ResponseStatusName(String name) {
        this.name = name;
    }
}
