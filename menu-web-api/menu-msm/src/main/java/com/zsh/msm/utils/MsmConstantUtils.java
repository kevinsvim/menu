package com.zsh.msm.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author:张帅豪
 * @date:2021/8/16
 * @email:zsh.smile.coder@gmail.com
 */
@Component
public class MsmConstantUtils implements InitializingBean {

    @Value("${tencent.msm.id}")
    private String secretID ;

    @Value("${tencent.msm.secret}")
    private String secretKey ;

    @Value("${tencent.msm.endPoint}")
    private String endPoint;

    @Value("${tencent.msm.appId}")
    private String appId;

    @Value("${tencent.msm.signName}")
    private String signName;

    @Value("${tencent.msm.templateId}")
    private String templateId;
    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String END_POINT;
    public static String APP_ID;
    public static String SIGN_NAME;
    public static String TEMPLATE_ID;
    @Override
    public void afterPropertiesSet() {
        SECRET_ID = secretID;
        SECRET_KEY = secretKey;
        END_POINT = endPoint;
        APP_ID = appId;
        SIGN_NAME = signName;
        TEMPLATE_ID = templateId;
    }
}
