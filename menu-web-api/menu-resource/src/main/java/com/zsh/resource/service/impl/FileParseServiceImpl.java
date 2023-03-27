package com.zsh.resource.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.excel.CategoryExcel;
import com.zsh.resource.exception.ExcelException;
import com.zsh.resource.listen.CategoryExcelListener;
import com.zsh.resource.service.FileParseService;
import com.zsh.resource.service.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by zsh on 2023/3/26
 *
 * @author zsh
 */
@Service
public class FileParseServiceImpl implements FileParseService {

    // 解析Excel中的分类数据，存储到数据库中
    private final TypeService typeService;
    public FileParseServiceImpl(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public CommonResult<Object> parseExcelToDB(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, CategoryExcel.class, new CategoryExcelListener(typeService)).sheet().doRead();
        } catch (Exception e) {
            throw new ExcelException("解析Excel异常");
        }
        return CommonResult.success("上传成功");
    }
}
