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
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        //Bean的使用
        student.play();
        System.out.println(student);
        //关闭容器
        ((AbstractApplicationContext) context).close();
    }
    /*
     * 七月 10, 2022 9:24:25 上午 org.springframework.context.support.AbstractApplicationContext prepareRefresh
     * 信息: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@c46bcd4: startup date [Sun Jul 10 09:24:25 CST 2022]; root of context hierarchy
     * 设置对象属性setName()..
     * 调用BeanNameAware的setBeanName()...
     * Student这个Bean：初始化
     * Student这个Bean：使用
     * Student [name = 彭涛]
     * 七月 10, 2022 9:24:25 上午 org.springframework.context.support.AbstractApplicationContext doClose
     * 信息: Closing org.springframework.context.support.ClassPathXmlApplicationContext@c46bcd4: startup date [Sun Jul 10 09:24:25 CST 2022]; root of context hierarchy
     * Student这个Bean：销毁
     * Disconnected from the target VM, address: 'javadebug', transport: 'shared memory'
     *
     * Process finished with exit code 0
     */
}

