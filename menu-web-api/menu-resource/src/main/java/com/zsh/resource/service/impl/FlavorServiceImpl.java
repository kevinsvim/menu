package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.Flavor;
import com.zsh.resource.service.FlavorService;
import com.zsh.resource.mapper.FlavorMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【flavor】的数据库操作Service实现
* @createDate 2023-03-17 10:55:32
*/
@Service
public class FlavorServiceImpl extends ServiceImpl<FlavorMapper, Flavor>
    implements FlavorService{

}




