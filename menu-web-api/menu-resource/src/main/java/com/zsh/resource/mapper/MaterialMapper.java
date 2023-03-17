package com.zsh.resource.mapper;

import com.zsh.resource.domain.Material;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18179
* @description 针对表【material】的数据库操作Mapper
* @createDate 2023-03-17 14:48:18
* @Entity com.zsh.resource.domain.Material
*/
@Mapper
public interface MaterialMapper extends BaseMapper<Material> {

}




