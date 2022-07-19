package beanLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
//@Configuration
//public class StudentBeanPostProcessor implements BeanPostProcessor {
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("执行方法postProcessBeforeInitialization()");
//        beanName = "student";
//        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("执行方法postProcessBeforeInitialization()");
//        beanName = "student";
//        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
//    }
//}

@Configuration
public class StudentBeanPostProcessor implements BeanPostProcessor {

    public StudentBeanPostProcessor() {
        super();
        System.out.println("这是BeanPostProcessor实现类构造器！！");
        // TODO Auto-generated constructor stub
    }

    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1)
            throws BeansException {
        System.out
                .println("BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！");
        return arg0;
    }

    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1)
            throws BeansException {
        System.out
                .println("BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！");
        return arg0;
    }
}
