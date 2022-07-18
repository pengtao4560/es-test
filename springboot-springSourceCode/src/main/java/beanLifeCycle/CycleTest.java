package beanLifeCycle;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 生命周期 demo 测试类
 * @author LinJie
 *
 */
public class CycleTest {
    @Test
    public void testBeanLifeCycle() {
        ApplicationContext context = new ClassPathXmlApplicationContext("xml/Student.xml");
        Student student = (Student) context.getBean("student");
        //Bean的使用
        student.play();
        System.out.println(student);
        //关闭容器
        ((AbstractApplicationContext) context).close();
    }
    /*
    七月 18, 2022 4:10:20 上午 org.springframework.context.support.AbstractApplicationContext prepareRefresh
    信息: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@4141d797: startup date [Mon Jul 18 04:10:20 CST 2022]; root of context hierarchy
    设置对象属性setName()..
    BeanNameAware的setBeanName()...
    BeanFactoryAware 的 setBeanFactory()...
    InitializingBean 接口 的 afterPropertiesSet()...
    Student这个Bean：初始化
    Student这个Bean：使用
    Student [name = 彭涛]
    七月 18, 2022 4:10:21 上午 org.springframework.context.support.AbstractApplicationContext doClose
    信息: Closing org.springframework.context.support.ClassPathXmlApplicationContext@4141d797: startup date [Mon Jul 18 04:10:20 CST 2022]; root of context hierarchy
    DisposableBean 接口 的 destroy()...
    Student这个Bean：销毁
     */
}

