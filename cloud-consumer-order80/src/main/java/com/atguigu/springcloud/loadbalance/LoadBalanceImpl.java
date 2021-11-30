package com.atguigu.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pt
 * @createdate 2021/11/29 0029
 * @desc
 */
@Component
public class LoadBalanceImpl implements LoadBalance {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int currrent = 0;
        int next;
        do {

            currrent = this.atomicInteger.get();
            next = currrent >= Integer.MAX_VALUE ? 0 : currrent + 1;

        } while (!this.atomicInteger.compareAndSet(currrent, next));
        System.out.println("*** 第几次访问，次数 next: " + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }

    public static void main(String[] args) {
        // 2147483647
        System.out.println(Integer.MAX_VALUE);
    }

}
