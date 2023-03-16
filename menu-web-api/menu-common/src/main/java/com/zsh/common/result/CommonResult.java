package com.zsh.common.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zsh on 2023/3/6
 *
 * @author zsh
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;
    private boolean status;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> CommonResult<T> success() {
        return success(CommonResultCode.SUCCESS.getCode(), CommonResultCode.SUCCESS.getMessage(), null);
    }

    public static <T> CommonResult<T> success(T data) {
        return success(CommonResultCode.SUCCESS.getCode(), CommonResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> success(CommonResultCode commonResultCode) {
        return success(commonResultCode.getCode(), commonResultCode.getMessage(), null);
    }

    public static <T> CommonResult<T> success(String msg, T data) {
        return success(CommonResultCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> CommonResult<T> success(Integer code, String msg) {
        return success(code, msg, null);
    }

    public static <T> CommonResult<T> success(Integer code, String msg, T data) {
        CommonResult<T> r = new CommonResult<T>();
        r.setStatus(true);
        r.setCode(code);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }


    public static <T> CommonResult<T> fail() {
        return fail(CommonResultCode.FAIL.getCode(), CommonResultCode.FAIL.getMessage(), null);
    }

    public static <T> CommonResult<T> fail(T data) {
        return fail(CommonResultCode.FAIL.getCode(), CommonResultCode.FAIL.getMessage(), data);
    }

    public static <T> CommonResult<T> fail(Integer code, String msg) {
        return fail(code, msg, null);
    }

    public static <T> CommonResult<T> fail(CommonResultCode commonResultCode) {
        return fail(commonResultCode.getCode(), commonResultCode.getMessage(), null);
    }

    public static <T> CommonResult<T> fail(String msg, T data) {
        return fail(CommonResultCode.FAIL.getCode(), msg, data);
    }

    public static <T> CommonResult<T> fail(Integer code, String msg, T data) {
        CommonResult<T> r = new CommonResult<T>();
        r.setStatus(false);
        r.setCode(code);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }
}
