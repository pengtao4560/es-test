package beanLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author LinJie
 * @Description:一个学生类(Bean)，能体现其生命周期的Bean
 */
public class Student implements BeanNameAware, BeanFactoryAware, InitializingBean {
    private String name;

    //无参构造方法
    public Student() {
        super();
    }

    /** 设置对象属性
     * @param name the name to set
     */
    public void setName(String name) {
        System.out.println("设置对象属性setName()..");
        this.name = name;
    }

    //Bean的初始化方法
    public void initStudent() {
        System.out.println("Student这个Bean：初始化");
    }

    //Bean的销毁方法
    public void destroyStudent() {
        System.out.println("Student这个Bean：销毁");
    }

    //Bean的使用
    public void play() {
        System.out.println("Student这个Bean：使用");
    }

    /* 重写toString
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Student [name = " + name + "]";
    }

    //调用BeanNameAware的setBeanName()
    //传递Bean的ID。
    @Override
    public void setBeanName(String name) {
        System.out.println("调用BeanNameAware的setBeanName()..." );
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware 的 setBeanFactory()..." );

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean接口 的 afterPropertiesSet()..." );

    }
}
