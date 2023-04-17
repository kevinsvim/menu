package com.zsh.resource.domain.vo;

import com.zsh.resource.domain.Material;
import com.zsh.resource.domain.Step;
import lombok.Data;

import java.util.List;

/**
 * Created by zsh on 2023/4/13
 *
 * @author zsh
 */
@Data
public class DishAuditVo {
    /**
     * 菜谱id
     */
    private String id;
    /**
     * 发布者id
     */
    private String publisherId;
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
    private String productionLevelId;
    /**
     * 烹饪等级
     */
    private String productionTimeId;
    /**
     * 描述
     */
    private String description;
    /**
     * 诀窍
     */
    private String skill;
    /**
     * 是否独家发布
     */
    private Boolean isSole;
    /**
     * 品味id
     */
    private String flavorId;
    /**
     * 烹饪工艺
     */
    private String cookTechnologyId;
    /**
     * 分类id
     */
    private String  typeId;
}
