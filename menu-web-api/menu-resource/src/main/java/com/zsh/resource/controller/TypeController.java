package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Type;
import com.zsh.resource.domain.dto.SearchSuggestDto;
import com.zsh.resource.domain.vo.SuggestVo;
import com.zsh.resource.service.TypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zsh on 2023/3/21
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/category")
public class TypeController {

    private final TypeService typeService;
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    /**
     * 获取树形列表
     */
    @GetMapping("/getTreeCategory/{status}")
    public CommonResult<Object> getTreeCategory(@PathVariable("status") String status) {
        return CommonResult.success(typeService.getTreeCategory(Integer.valueOf(status)));
    }

    /**
     * 保存分类
     */
    @PostMapping("/saveCategory")
    public CommonResult<Object> saveCategory(@RequestBody Type type) {
        return typeService.saveCategory(type);
    }

    /**
     * 移除分类
     */
    @DeleteMapping("/removeCategory")
    public CommonResult<Object> removeCategory(@RequestParam("categoryLevel") Integer categoryLevel, @RequestParam("categoryId") Long categoryId) {
        return typeService.removeCategory(categoryLevel, categoryId);
    }

    /**
     * 给予用户实时搜索建议
     */
    @PostMapping("/getSuggestList")
    public CommonResult<Object> getSuggest(@RequestBody SearchSuggestDto searchSuggestDto) {
        List<SuggestVo> list = typeService.getSearchSuggestList(searchSuggestDto);
        return CommonResult.success(list);
    }
}
