package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.ComicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zsh on 2023/4/4
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/comic")
public class ComicController {

    private final ComicService comicService;
    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }
    @GetMapping("/getAllComic")
    public CommonResult<Object> getAllComic() {
        return CommonResult.success(comicService.list());
    }
}
