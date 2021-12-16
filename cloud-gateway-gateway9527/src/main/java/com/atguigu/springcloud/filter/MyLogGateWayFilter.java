package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

/**
 * gateway自定义过滤器： 作用：全局日志记录、统一网关鉴权 ....
 * @author pt
 * @createdate 2021/12/16 0016
 * @desc
 */
@Configuration
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("****come in MyLogGateWayFilter" + ZonedDateTime.now());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if(uname == null){
            log.info("*****用户名为null,非法用户");
             exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
             return exchange.getResponse().setComplete();
        }
        //传下去，第二个过滤连再去验证
        return chain.filter(exchange);
    }

    /**Useful constant for the highest precedence value. 加载过滤器的顺序，数字越小，优先级越高
     * 用于最高优先级值的有用常量  */
    @Override
    public int getOrder() {
        return 0;
    }
}
