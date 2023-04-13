package com.zsh.resource.domain.dto;

import lombok.Data;

/**
 * Created by zsh on 2023/4/11
 *
 * @author zsh
 */
@Data
public class SearchSuggestDto {

    /**
     * 匹配的字符串
     */
    private String content;

    /**
     * 要返回的数据量
     */
    private Integer size;
}
