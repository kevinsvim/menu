package com.zsh.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Dish;
import com.zsh.resource.domain.dto.PersonalDto;
import com.zsh.resource.domain.dto.PublishDishDto;
import com.zsh.resource.domain.vo.*;
import com.zsh.resource.domain.vo.personal.PersonalVo;
import com.zsh.resource.recommend.DataPreprocess.DishDataPreProcess;
import com.zsh.resource.service.DishService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by zsh on 2023/3/13
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/dish")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @Resource
    private DishDataPreProcess dishDataPreProcess;
    /**
     * 测试
     */
    @GetMapping("/test")
    public CommonResult<Object> test() {
        dishDataPreProcess.process();
        return CommonResult.success();
    }
    /**
     * 获取热度值推荐
     */
    @GetMapping("/hotScore")
    public CommonResult<Object> getHotRec() {

        return dishService.getHotRec();
    }

    /**
     * 存储发布的菜品
     */
    @PostMapping("/saveDish")
    public CommonResult<Object> saveDish(@RequestHeader("userId") String userId, @RequestBody PublishDishDto publishDishDto) {
        publishDishDto.setPublisherId(userId);
        return dishService.saveDish(publishDishDto);
    }

    /**
     * 获取精选菜谱
     */
    @GetMapping("/getConcentrationMenu")
    public CommonResult<Object> getConcentrationMenu() {
        List<DishConcentrationVo> list = dishService.getConcentrationMenu();
        return CommonResult.success(list);
    }

    /**
     * 获取菜谱详情数据
     *
     * @param id 菜谱id
     */
    @GetMapping("/getDishDetailById/{id}")
    public CommonResult<Object> getDishDetailById(@PathVariable("id") String id, @RequestHeader(value = "userId", required = false) String userId) {
        DishDetailVo list = dishService.getDishDetail(id, userId);
        return CommonResult.success(list);
    }

    /**
     * 根据三级分类id获取所有菜谱
     */
    @GetMapping("/getAllDishByCategoryId/{categoryId}/{pageSize}/{currentPage}")
    public CommonResult<Object> getAllDishByCategoryId(@PathVariable("categoryId") String categoryId,
                                                       @PathVariable("pageSize") Integer pageSize,
                                                       @PathVariable("currentPage") Integer currentPage) {
        IPage<DishCategoryVo> data = dishService.getAllDishByCategoryId(categoryId, pageSize, currentPage);
        return CommonResult.success(data);
    }

    /**
     * 个人主页信息获取
     */
    @PostMapping("/getPersonalInfo")
    private CommonResult<Object> getPersonalInfo(@RequestHeader("userId") String userId, @RequestBody PersonalDto personalDto) {

        PersonalVo personalVo = dishService.getPersonal(userId, personalDto);
        return CommonResult.success(personalVo);
    }

    /**
     * 获取推荐的用户
     */
    @GetMapping("/getRecMember")
    public CommonResult<Object> getRecMember() {
        List<MemberRecVo> memberRecVos = dishService.getRecMember();
        return CommonResult.success(memberRecVos);
    }

    /**
     * 获取菜谱分页数据
     */
    @GetMapping("/admin/getPageDish/{currentPage}/{pageSize}")
    public CommonResult<Object> getPageDish(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize")Integer pageSize) {
        IPage<Dish> page = new Page<>(currentPage, pageSize);
        dishService.page(page);
        return CommonResult.success(page);
    }
    /**
     * 删除
     */
    @DeleteMapping("/admin/removeDishById/{id}")
    public CommonResult<Object> removeDishById(@PathVariable("id") String id) {
        dishService.deleteDish(id);
        return CommonResult.success("删除成功");
    }

    /**
     * 更新
     */
    @PutMapping("/admin/auditDish/{id}/{status}")
    public CommonResult<Object> auditDish(@PathVariable("id") String id, @PathVariable("status") Integer status) {
        Dish dish = new Dish();
        dish.setId(id);
        dish.setStatus(status);
        dishService.updateById(dish);
        return CommonResult.success("更新成功");
    }
}
