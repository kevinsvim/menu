package com.zsh.resource.domain.dto;

import com.zsh.resource.domain.Material;
import com.zsh.resource.domain.Step;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by zsh on 2023/3/17
 *
 * @author zsh
 */
@Data
public class PublishDishDto {
    /**
     * 菜品名
     */
    private String name;
    /**
     * 菜品图片url
     */
    private String imageUrl;
    /**
     * 烹饪水平
     */
    private Long productionLevelId;
    /**
     * 烹饪等级
     */
    private Long productionTimeId;
    /**
     * 描述
     */
    private String description;
    /**
     * 材料列表
     */
    private List<Material> materialMenu;
    /**
     * 步骤列表
     */
    private List<Step> stepMenu;
    /**
     * 诀窍
     */
    private String skill;
    /**
     * 厨具集合
     */
    private List<Long> kitchenwareIds;
    /**
     * 是否独家发布
     */
    private Boolean isSole;
    /**
     * 品味id
     */
    private Long flavorId;
    /**
     * 烹饪工艺id
     */
    private Long cookTechnologyId;
}
