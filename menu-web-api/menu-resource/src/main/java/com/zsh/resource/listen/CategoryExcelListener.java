package com.zsh.resource.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsh.resource.domain.Type;
import com.zsh.resource.domain.excel.CategoryExcel;
import com.zsh.resource.exception.ExcelException;
import com.zsh.resource.service.TypeService;
import org.apache.commons.lang3.ObjectUtils;

/**
 * Created by zsh on 2023/3/26 22:26
 * 1. 一级分类重复 -- 二级分类没要求 -- 三级分类不能重复
 * 2. 一级分类不重复 -- 二级分类不能重复 -- 三级分类不能重复
 */
public class CategoryExcelListener extends AnalysisEventListener<CategoryExcel> {
    /**
     * 因为Listener的初始化是在spring之前，所以不能加载到ioc容器中，所以无法注入到ioc容器
     * 因此为了使用spring的功能，我们需要手动引入service
     */
    private TypeService typeService;

    public CategoryExcelListener() {}

    public CategoryExcelListener(TypeService typeService) {
        this.typeService = typeService;
    }

    /**
     * 当excel中的每一行数据被读取时，会被该监听器监听到，之后就会执行invoke方法。
     */
    @Override
    public void invoke(CategoryExcel categoryExcel, AnalysisContext analysisContext) {
        if (categoryExcel == null) {
            throw new ExcelException("上传的Excel存在数据空");
        }
        // 获取每个类名对应的数据
        Type oneCategory = getType(typeService, categoryExcel.getOneCategoryName());
        Type twoCategory = getType(typeService, categoryExcel.getTwoCategoryName());
        Type threeCategory = getType(typeService, categoryExcel.getThreeCategoryName());
        // 一级分类重复
        if (ObjectUtils.isNotEmpty(oneCategory)) {
            // 二级分类重复
            if (ObjectUtils.isNotEmpty(twoCategory) && twoCategory.getParentId().equals(oneCategory.getId())) {
                // 不存在三级分类
                if (ObjectUtils.isEmpty(threeCategory)) {
                    saveType(typeService, categoryExcel.getThreeCategoryName(), twoCategory.getId(), 3);
                }
            } else {
                // 二级分类不重复,则三级不能重复
                if (ObjectUtils.isEmpty(threeCategory)) {
                    // 保存二级分类
                    Type type2 = saveType(typeService, categoryExcel.getTwoCategoryName(), oneCategory.getId(), 2);
                    // 保存三级分类
                    saveType(typeService, categoryExcel.getThreeCategoryName(), type2.getId(), 3);
                }
            }
        } else {
            // 一级分类不重复,那么二、三级分类都不能重复
            if (ObjectUtils.isEmpty(twoCategory) && ObjectUtils.isEmpty(threeCategory)) {
                // 将三者保存起来
                // 保存一级分类
                Type type1 = saveType(typeService, categoryExcel.getOneCategoryName(), 0L, 1);
                // 保存二级分类
                Type type2 = saveType(typeService, categoryExcel.getTwoCategoryName(), type1.getId(), 2);
                // 保存三级分类
                saveType(typeService, categoryExcel.getThreeCategoryName(), type2.getId(), 3);
            }
        }
    }

    /**
     * 统一保存数据
     */
    private Type saveType(TypeService typeService, String name, Long parentId, int level) {
        Type type = new Type();
        type.setName(name);
        type.setTypeLevel(level);
        type.setParentId(parentId);
        typeService.save(type);
        return type;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /**
     * 根据分类名获取数据
     */
    private Type getType(TypeService typeService, String name) {
        LambdaQueryWrapper<Type> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Type::getName, name);
        return typeService.getOne(wrapper);
    }
}
