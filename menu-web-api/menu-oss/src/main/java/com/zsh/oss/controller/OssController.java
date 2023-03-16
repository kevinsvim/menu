package com.zsh.oss.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.oss.service.OssService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author:张帅豪
 * @date:2023/3/19
 */
@RestController
@RequestMapping("/oss")
public class OssController {
    @Resource
    private OssService ossService;

    @PostMapping("/upload/avatar")
    public CommonResult<String> uploadAvatarToOss(MultipartFile file) {
        String url = ossService.uploadAvatarToOss(file);
        return CommonResult.success(url);
    }


    @DeleteMapping("/removeImage/{fileUrl}/**")
    public CommonResult<Object> removeImage(@PathVariable("fileUrl") String fileUrl) {
        return ossService.removeImage(fileUrl);
    }
}
