package com.zsh.oss.service.impl;

import com.zsh.common.result.CommonResult;
import com.zsh.oss.service.OssService;
import com.zsh.oss.utils.AliyunOssUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author:张帅豪
 * @date:2021/6/19
 * @email:1817937322@qq.com
 */
@Service
public class OssServiceImpl implements OssService {

    /**
     *  文件上传
     */
    @Override
    public String uploadAvatarToOss(MultipartFile file) {
        return AliyunOssUtils.uploadFile(file);
    }

    /**
     * 根据图片url移除oss中的图片
     * @param fileUrl 文件url
     */
    @Override
    public CommonResult<Object> removeImage(String fileUrl) {
        AliyunOssUtils.deleteFile(fileUrl);
        return CommonResult.success();
    }
}
