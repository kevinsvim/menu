package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Type;
import com.zsh.resource.domain.vo.CategoryTreeVo;
import com.zsh.resource.service.TypeService;
import com.zsh.resource.mapper.TypeMapper;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author 18179
* @description 针对表【type】的数据库操作Service实现
* @createDate 2023-03-21 22:54:35
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{


    /**
     * 获取树形分类
     * 一级分类父id: 0
     * 二级分类的父id为一级分类的id
     * 三级分类的父id为二级分类的id
     */
    @Override
    public CommonResult<Object> getTreeCategory() {
        List<CategoryTreeVo> oneCategory = new ArrayList<>();
        // 1. 查出所有数据
        List<Type> list = this.list();
        // 2.将pid为0的设置为一级分类,并将一级分类的id放入hash表中
        list.forEach(type -> {
            if (type.getParentId() == 0) {
                CategoryTreeVo category = new CategoryTreeVo();
                category.setId(type.getId());
                category.setName(type.getName());
                category.setParentId(0L);
                // 寻找二级分类数据
                list.forEach(twoItem -> {
                    if (twoItem.getTypeLevel() == 2 && Objects.equals(type.getId(), twoItem.getParentId())) {
                        if (category.getChildren() == null) {
                            category.setChildren(new ArrayList<>());
                        }
                        CategoryTreeVo category2 = new CategoryTreeVo();
                        category2.setId(twoItem.getId());
                        category2.setName(twoItem.getName());
                        category2.setParentId(twoItem.getParentId());
                        // 寻找三级分类数据
                        list.forEach(threeItem -> {
                            if (threeItem.getTypeLevel() == 3 && Objects.equals(twoItem.getId(), threeItem.getParentId())) {
                                if (category2.getChildren() == null) {
                                    category2.setChildren(new ArrayList<>());
                                }
                                CategoryTreeVo category3 = new CategoryTreeVo();
                                category3.setId(threeItem.getId());
                                category3.setName(threeItem.getName());
                                category3.setParentId(threeItem.getParentId());
                                category2.getChildren().add(category3);
                            }
                        });
                        category.getChildren().add(category2);
                    }
                });
                oneCategory.add(category);
            }

        });

        return CommonResult.success(oneCategory);
    }
}




