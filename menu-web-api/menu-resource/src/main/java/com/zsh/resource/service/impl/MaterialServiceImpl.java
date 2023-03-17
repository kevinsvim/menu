package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.Material;
import com.zsh.resource.service.MaterialService;
import com.zsh.resource.mapper.MaterialMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【material】的数据库操作Service实现
* @createDate 2023-03-17 14:48:19
*/
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material>
    implements MaterialService{

}




