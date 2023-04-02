package com.zsh.resource.constant;

import lombok.Getter;

/**
 * Created by zsh on 2023/3/31
 *
 * @author zsh
 */
@Getter
public enum CommentEnum {
    // 未审核
    UNCHECKED(1),
    // 已审核
    APPROVED(2);

    public final int index;

    CommentEnum(int index) {
        this.index = index;
    }
}
