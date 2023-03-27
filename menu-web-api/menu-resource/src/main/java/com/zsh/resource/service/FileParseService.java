package com.zsh.resource.service;

import com.zsh.common.result.CommonResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zsh on 2023/3/26
 */
public interface FileParseService {

    CommonResult<Object> parseExcelToDB(MultipartFile file);
}
