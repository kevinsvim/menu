package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.BannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zsh on 2023/3/11
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/banner/front")
public class BannerFrontController {

    private final BannerService bannerService;
    public BannerFrontController(BannerService bannerService) {
        this.bannerService = bannerService;
    }
    @GetMapping("/bannerList")
    public CommonResult<List<String>> findAllBanner() {
        List<String> bannerList = bannerService.findList();
        return CommonResult.success(bannerList);
    }
}
