package com.springcloud.atguigu.cloudalibabaproviderpayment9002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosPayment9002Application {

    public static void main(String[] args) {
        SpringApplication.run(NacosPayment9002Application.class, args);
    }

}
