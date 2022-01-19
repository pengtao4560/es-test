package com.atguigu.springcloud.config;

import feign.Feign;
import feign.Logger;
import feign.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pt
 * @createdate 2021/11/30 0030
 * @desc openFeign的日志级别配置文件
 */
@Configuration
@Slf4j
public class FeignConfig {
    @Bean
    feign.Logger.Level feignLoggerLevel(){
        // CTRL 查看 Logger.Level
        return Logger.Level.FULL;
    }
    @Bean
    Request.Options getOption(){
        Request.Options options = new Request.Options(3500, 3500);
        return options;
    }


}
