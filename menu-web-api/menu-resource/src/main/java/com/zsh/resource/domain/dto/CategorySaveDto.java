package com.zsh.resource.domain.dto;

import lombok.Data;

/**
 * Created by zsh on 2023/3/26
 *
 * @author zsh
 */
@Data
public class CategorySaveDto {

    private String name;
    private Long parentId;
}
