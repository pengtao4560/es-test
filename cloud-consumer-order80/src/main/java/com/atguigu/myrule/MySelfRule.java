package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义 ribbon 负载均衡规则
 * @author pt
 * @createdate 2021/11/27 0027
 * @desc
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        // 定义为随机
        return new RoundRobinRule();
    }

}
