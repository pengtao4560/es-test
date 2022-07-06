package com.atguigu.springcloud.aop;

import cn.hutool.json.JSONUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * @Describe 自定义日志切面类
 * @Author shiyong
 * @Version 1.0
 * @Since JDK1.8, MySQL5.7, Maven3.6, Git2.14, Spring5, SpringBoot2.2, SpringCloud(G)
 * @Company TAIKANG LIFE
 * @Copyright (c) 2020 TaiKang Life Co'Ltd Inc. All rights reserved.
 */
@Aspect
@Component
@Slf4j
public class AccessLogAOP {
    //线程局部的变量，用于解决多线程中相同变量的访问冲突问题；
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    private static final String START_TIME = "request-start";
    private static String profile;
    private static final String PROD_PROFILE = "prod";

    /**
     * 定义切入点
     */
    @Pointcut(value = "@within(com.atguigu.springcloud.aop.AccessLog) || @annotation(com.atguigu.springcloud.aop.AccessLog)")
    public void logAspect() {

    }

    /**
     * 在方法前执行
     */
    @Before("logAspect()")
    public void beforeLog(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        //记录下请求的内容
        log.info("[请求URL]：" + request.getRequestURL().toString());
        log.info("[请求 IP]：" + request.getRemoteAddr());
        log.info("[请求方法]：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "()");
        Object[] arrayObj = joinPoint.getArgs();
        for (int t = 0; t < arrayObj.length; t++) {
            log.info("[请求参数]：arg" + (t + 1) + "=" + JSONUtil.toJsonStr(arrayObj));
        }
    }

    /**
     * 环绕通知，在执行前后都可以执行(其中可以加上需要的业务逻辑)
     */
    @Around("logAspect()")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("[方法开始时间]：" + new Date());
        Object object = proceedingJoinPoint.proceed();
        log.info("[方法结束时间]：" + new Date());
        return object;
    }

    /**
     * 在方法执行后返回一个结果时执行
     */
    @AfterReturning(pointcut = "logAspect()", returning = "returnObject")
    public void doAfterReturning(Object returnObject) throws Throwable {
        log.info("[请求返回]：" + returnObject);
        log.info("[请求耗时]：" + (System.currentTimeMillis() - startTime.get()) + "ms");
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        log.info("[请求浏览器类型]：" + userAgent.getBrowser().toString());
        log.info("[请求的操作系统]：" + userAgent.getOperatingSystem().toString());
        log.info("[原始USER-AGENT]: " + header);
    }

    /**
     * 在方法执行过程中抛出异常的时候执行
     */
    @AfterThrowing(pointcut = "logAspect()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.error("@执行出现异常:[{}]", ex);
    }
}
