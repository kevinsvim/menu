package com.zsh.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.resource.domain.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 18179
* @description 针对表【banner】的数据库操作Mapper
* @createDate 2023-03-11 10:25:43
* @Entity generator.domain.Banner
*/
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {
    List<String> findList();
}




