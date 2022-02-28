package com.atguigu.proxy.staticproxy.dynamic;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式-代理工厂
 *
 * @author pengtao
 * @createdate 2022/02/20 0020
 */
@Slf4j
public class ProxyFactory {

    /** 维护一个目标对象，Object */
    private Object target;

    /** 在构造器中，对target 进行初始化 */
    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标对象生成一个代理对象
    public Object getProxyInstance() {
        /**
         * 说明
         * @see Proxy#newProxyInstance(java.lang.ClassLoader, java.lang.Class[], java.lang.reflect.InvocationHandler)
         * @param ClassLoader 指定当前目标对象使用的类加载器，获取加载器的方法固定
         * @param Class<?>[] interfaces:目标对象实现的接口类型，使用泛型方法确认类型
         * @param InvocationHandler h :事情处理，执行目标对象的方法时，会触发事情处理器方法,会把当前执行的目标对象方法作为参数来传递
         * */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        log.info("jdk代理开始");
                        // 反射机制调用目标对象的方法
                        Object invoke = method.invoke(target, args);
                        return invoke;
                    }
                });
    }
}
