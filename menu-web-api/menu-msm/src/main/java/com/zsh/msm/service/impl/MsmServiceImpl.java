package com.zsh.msm.service.impl;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.zsh.msm.service.MsmService;
import com.zsh.msm.utils.MsmConstantUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zsh on 2023/02/24
 *
 * @author zsh
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Override
    public boolean send(String phone, String code) {
        try {
            //这里是实例化一个Credential，也就是认证对象，参数是密钥对；你要使用肯定要进行认证
            Credential credential = new Credential(MsmConstantUtils.SECRET_ID, MsmConstantUtils.SECRET_KEY);

            //HttpProfile这是http的配置文件操作，比如设置请求类型(post,get)或者设置超时时间了、还有指定域名了
            //最简单的就是实例化该对象即可，它的构造方法已经帮我们设置了一些默认的值
            HttpProfile httpProfile = new HttpProfile();
            //这个setEndpoint可以省略的
            httpProfile.setEndpoint(MsmConstantUtils.END_POINT);

            //实例化一个客户端配置对象,这个配置可以进行签名（使用私钥进行加密的过程），对方可以利用公钥进行解密
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            //实例化要请求产品(以sms为例)的client对象
            SmsClient smsClient = new SmsClient(credential, "ap-guangzhou", clientProfile);

            //实例化request封装请求信息
            SendSmsRequest request = new SendSmsRequest();
            // 头部加上86表示国内
            String[] phoneNumber = {"86" + phone};
            request.setPhoneNumberSet(phoneNumber);
            request.setSmsSdkAppid(MsmConstantUtils.APP_ID);
            request.setSign(MsmConstantUtils.SIGN_NAME);
            request.setTemplateID(MsmConstantUtils.TEMPLATE_ID);
            //生成随机验证码，我的模板内容的参数只有一个
            String[] templateParamSet = {code};
            request.setTemplateParamSet(templateParamSet);

            //发送短信
            smsClient.SendSms(request);
            return true;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
