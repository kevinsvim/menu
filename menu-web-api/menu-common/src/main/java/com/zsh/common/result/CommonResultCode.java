package com.zsh.common.result;

/**
 * Created by zsh on 2023/3/6
 *
 * @author zsh
 */
public enum CommonResultCode {

    SUCCESS(200, "成功"),
    FAIL(412, "失败"),

    LOGIN_ERROR(202, "用户名或密码错误"),

    UNKNOWN_ERROR(500, "未知错误"),

    TOKEN_INVALID(412, "token 已过期或验证不正确!"),

    TOKEN_SIGNATURE_INVALID(403, "无效的签名"),

    UNAUTHORIZED(401, "未认证"),

    REFRESH_TOKEN_INVALID(412, "refreshToken 无效"),

    LOGIN_TYPE_ERROR(30001, "用户登录类型错误"),

    VERIFY_CODE_ERROR(30002, "验证码错误");

    private final int code;
    private final String message;
    CommonResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
