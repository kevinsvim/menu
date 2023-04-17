package com.zsh.resource.handler.exception;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.exception.ExcelException;
import com.zsh.resource.exception.RemoveDishException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by zsh on 2023/3/26
 * 全局异常控制器
 * @author zsh
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 解析Excel异常
     */
    @ExceptionHandler(ExcelException.class)
    public CommonResult<Object> parseExcelException(ExcelException excelException) {
        return CommonResult.fail(excelException.getMessage());
    }

    /**
     * 删除食谱移除
     */
    @ExceptionHandler(RemoveDishException.class)
    public CommonResult<Object> removeDishException(RemoveDishException e) {
        return CommonResult.success(e.getMessage());
    }

    /**
     * 未知异常
     */
    @ExceptionHandler(Exception.class)
    public CommonResult<Object> exception(ExcelException e) {
        return CommonResult.success(e.getMessage());
    }
}

