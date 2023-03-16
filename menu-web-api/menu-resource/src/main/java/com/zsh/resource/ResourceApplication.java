package com.zsh.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by zsh on 2023/3/11
 *
 * @author zsh
 */
 // 扫描common模块下的配置
@ComponentScan({"com.zsh"})
// 开启定时器功能
@EnableScheduling
// 启用异步任务
@EnableAsync
@SpringBootApplication
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class);
    }
}
