package com.zsh.msm.service;

/**
 * Created by zsh on 2023/2/24
 * @author zsh
 */
public interface MsmService {
    /**
     * 发送验证码
     * @param phone 手机号
     * @param code 验证码
     * @return 是否发送成功
     */
    boolean send(String phone, String code);
}
