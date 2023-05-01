package com.zsh.resource.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsh on 2023/4/30
 * 菜谱数据预处理
 * @author zsh
 */
@Data
public class DishPreData {

    /**
     * 菜谱id
     */
    private String id;

    /**
     * 菜谱名
     */
    private String name;

    /**
     * 分类id
     */
    private String typeId;

    /**
     * 菜谱材料
     */
    private String materialName;

    /**
     * 菜谱步骤
     */
    private List<String> steps = new ArrayList<>();

    /**
     * 菜谱标签
     */
    private List<String> tags = new ArrayList<>();
}
