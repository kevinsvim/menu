package com.zsh.resource.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zsh on 2023/4/11
 *
 * @author zsh
 */
@Data
public class SuggestVo implements Serializable {
    private String id;
    private String value;
}
