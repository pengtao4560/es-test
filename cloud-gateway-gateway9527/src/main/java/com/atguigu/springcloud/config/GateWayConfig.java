package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pt
 * @createdate 2021/12/15 0015
 * @desc gateway配置
 */
@Configuration
public class GateWayConfig {
    /***/
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        String uri = "https://news.baidu.com/guonei";

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("path_route_atguigu",
                r -> r.path("/guonei")
                .uri(uri)).build();
        return routes.build();
    }
}
