package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Dish;
import com.zsh.resource.domain.Type;
import com.zsh.resource.domain.vo.CategoryTreeVo;
import com.zsh.resource.service.DishService;
import com.zsh.resource.service.TypeService;
import com.zsh.resource.mapper.TypeMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 18179
 * @description 针对表【type】的数据库操作Service实现
 * @createDate 2023-03-21 22:54:35
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
        implements TypeService {


    private final DishService dishService;

    public TypeServiceImpl(DishService dishService) {
        this.dishService = dishService;
    }

    /**
     * 获取树形分类
     * 一级分类父id: 0
     * 二级分类的父id为一级分类的id
     * 三级分类的父id为二级分类的id
     * @param status
     */
    @Cacheable(value = "treeData")
    @Override
    public List<CategoryTreeVo> getTreeCategory(Integer status) {
        List<CategoryTreeVo> oneCategory = new ArrayList<>();
        // 1. 查出所有数据
        List<Type> list = this.list();
        // 2.将pid为0的设置为一级分类,并将一级分类的id放入hash表中
        list.forEach(type -> {
            if (type.getParentId().equals("0")) {
                CategoryTreeVo category = new CategoryTreeVo();
                category.setId(type.getId());
                category.setName(type.getName());
                category.setParentId("0");
                category.setLevel(1);
                // 寻找二级分类数据
                list.forEach(twoItem -> {
                    if (twoItem.getTypeLevel() == 2 && Objects.equals(type.getId(), twoItem.getParentId())) {
                        CategoryTreeVo category2 = new CategoryTreeVo();
                        category2.setId(twoItem.getId());
                        category2.setName(twoItem.getName());
                        category2.setParentId(twoItem.getParentId());
                        category2.setLevel(2);
                        // 寻找三级分类数据
                        list.forEach(threeItem -> {
                            if (threeItem.getTypeLevel() == 3 && Objects.equals(twoItem.getId(), threeItem.getParentId())) {
                                CategoryTreeVo category3 = new CategoryTreeVo();
                                category3.setId(threeItem.getId());
                                category3.setName(threeItem.getName());
                                category3.setParentId(threeItem.getParentId());
                                category3.setLevel(3);
                                category3.setDisabled(status == 1);
                                category2.getChildren().add(category3);
                            }
                        });
                        category.getChildren().add(category2);
                    }
                });
                oneCategory.add(category);
            }

        });

        return oneCategory;
    }

    /**
     * 保存分类信息
     */
    @Override
    public CommonResult<Object> saveCategory(Type type) {
        // 1. 如果typeLevel == 1，说明是一级分类数据
        // 2. 如果typeLevel == 2 || typeLevel == 3说明是子级分类数据
        this.save(type);
        return CommonResult.success();
    }

    /**
     * 移除分类
     */
    @Override
    public CommonResult<Object> removeCategory(Integer categoryLevel, Long categoryId) {
        if (categoryLevel > 3) {
            return CommonResult.fail("不被同意的数值");
        }
        if (categoryLevel == 3) {
            // 当前level == 3,则查询该分类下是否存在菜谱
            LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
            dishWrapper.eq(Dish::getTypeId, categoryId);
            Dish dish = dishService.getOne(dishWrapper);
            if (ObjectUtils.isNotEmpty(dish)) {
                // 3.挂载有数据，不能够删除
                return CommonResult.success(false);
            }
        }
        this.removeById(categoryId);
        return CommonResult.success(true);
    }
}




