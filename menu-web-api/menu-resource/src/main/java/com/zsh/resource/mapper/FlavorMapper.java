package com.zsh.resource.mapper;

import com.zsh.resource.domain.Flavor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18179
* @description 针对表【flavor】的数据库操作Mapper
* @createDate 2023-03-17 10:55:32
* @Entity com.zsh.resource.domain.Flavor
*/
@Mapper
public interface FlavorMapper extends BaseMapper<Flavor> {

}




