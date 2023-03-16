package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.Banner;
import com.zsh.resource.mapper.BannerMapper;
import com.zsh.resource.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 18179
* @description 针对表【banner】的数据库操作Service实现
* @createDate 2023-03-11 10:25:43
*/
@Service
@Slf4j
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    private final BannerMapper bannerMapper;
    public BannerServiceImpl(BannerMapper bannerMapper) {
        this.bannerMapper = bannerMapper;
    }

    @Override
    @Cacheable(value = "banner", key = "'findList'")
    public List<String> findList() {
        return bannerMapper.findList();
    }
}




