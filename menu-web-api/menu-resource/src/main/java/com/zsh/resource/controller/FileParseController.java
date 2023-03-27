package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.FileParseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zsh on 2023/3/26
 * 解析文件控制器
 * @author zsh
 */
@RestController
@RequestMapping("/resource/file")
public class FileParseController {

    private final FileParseService fileParseService;
    public FileParseController(FileParseService fileParseService) {
        this.fileParseService = fileParseService;
    }

    /**
     * 解析前端传来的Excel文件进行解析保存到数据库
     */
    @PostMapping("/parseExcelToDB")
    public CommonResult<Object> parseExcelToDB(MultipartFile file) {
        return fileParseService.parseExcelToDB(file);
    }

}
