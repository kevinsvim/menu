package com.zsh.resource.handler.exception;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.exception.ExcelException;
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
    public CommonResult<Object> businessException(ExcelException excelException) {
        return CommonResult.fail(excelException.getMessage());
    }
}

