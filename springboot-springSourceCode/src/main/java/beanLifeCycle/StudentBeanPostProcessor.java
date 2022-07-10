package beanLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *
 */
public class StudentBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行方法postProcessBeforeInitialization()");
        beanName = "Student";
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行方法postProcessBeforeInitialization()");
        beanName = "Student";
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
