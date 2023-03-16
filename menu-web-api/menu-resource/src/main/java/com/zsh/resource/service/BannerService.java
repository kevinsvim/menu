package com.zsh.resource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsh.resource.domain.Banner;

import java.util.List;

/**
* @author 18179
* @description 针对表【banner】的数据库操作Service
* @createDate 2023-03-11 10:25:43
*/
public interface BannerService extends IService<Banner> {
    List<String> findList();
}
