package com.zsh.resource.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsh on 2023/3/21
 *
 * @author zsh
 */
@Data
public class CategoryTreeVo {

    private Long id;
    private String name;
    private Long parentId;
    private Integer level;
    private Boolean disabled;

    private List<CategoryTreeVo> children = new ArrayList<>();
}
