package com.atguigu.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author pt
 * @createdate 2021/11/29 0029
 * @desc 自定义负载均衡器
 */
public interface LoadBalance {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
