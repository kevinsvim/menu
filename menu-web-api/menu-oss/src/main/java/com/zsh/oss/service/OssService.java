package com.zsh.oss.service;

import com.zsh.common.result.CommonResult;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author:张帅豪
 * @date:2021/6/19
 * @email:1817937322@qq.com
 */
public interface OssService {
    /**
     * 上传图片到oss
     */
    String uploadAvatarToOss(MultipartFile file);

    /**
     * 移除图片操作
     */
    CommonResult<Object> removeImage(String fileUrl);
}
