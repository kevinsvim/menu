package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.SearchLog;
import com.zsh.resource.service.SearchLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zsh on 2023/4/12
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/searchLog")
public class SearchLogController {

    private final SearchLogService searchLogService;
    public SearchLogController(SearchLogService searchLogService) {
        this.searchLogService = searchLogService;
    }
    @GetMapping("/searchList")
    public CommonResult<Object> getSearchList() {
        List<SearchLog> list = searchLogService.selectList();
        return CommonResult.success(list);
    }
}
