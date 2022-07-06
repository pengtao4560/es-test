package com.atguigu.springcloud.aop;

import java.lang.annotation.*;

/**
 * @Describe 日志注解
 * @Version 1.0
 * @Since JDK1.8, MySQL5.7, Maven3.6, Git2.14, Spring5, SpringBoot2.2, SpringCloud(G)
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AccessLog {
/**
 * 也可自带注解的属性字段
 */
}
