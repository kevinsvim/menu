package com.zsh.resource.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author:zsh
 * 用来封装读取到的每一行Excel数据
 */
@Data
public class CategoryExcel {

    /**
     * Excel中第一列表头名称
     */
    @ExcelProperty(index = 0, value = "一级分类")
    private String oneCategoryName;

    /**
     * Excel中第二列表头名称
     */
    @ExcelProperty(index = 1, value = "二级分类")
    private String twoCategoryName;

    /**
     * Excel中的第三列表头名称
     */
    @ExcelProperty(index = 2, value = "三级分类")
    private String threeCategoryName;

}
