package javase.workproject;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 工作用到的API测试
 */
public class BeanUtilsTest {

    public static void main(String[] args) {
        Employee ee1 = new Employee("A", 21, "it");
        Employee ee2=new Employee("B",23,"account");
        User user=new User();
        BeanUtil.copyProperties(ee1, user);
        System.out.println(user);
        System.out.println("-------------分割线--------------");
        List<User> output=new ArrayList<>();
        List<Employee> source= Arrays.asList(ee1, ee2);
        output= BeanUtil.copyToList(source, User.class);
        for (User str : output) {
            System.out.println(str);
        }
        /*
        User(name=A, age=21)
        -------------分割线--------------
        User(name=A, age=21)
        User(name=B, age=23)
        * */
        String join = StringUtils.join(1, 11, '这', "是什么");
        System.out.println(join);
    }

    public void copyProperties(Object dest, Object source) throws InvocationTargetException, IllegalAccessException {
        /**
         *  对象属性拷贝 <br>
         *      * 将源对象的属性拷贝到目标对象
         *      *
         *      * @param source 源对象
         *      * @param target 目标对象
         * @see org.springframework.beans.BeanUtils#copyProperties(Object source, Object dest)
         * @see org.apache.commons.beanutils.BeanUtils#copyProperties(Object dest, Object source)
         * */
        // BeanUtils.copyProperties(Object source, Object target);
        // org.springframework.beans.BeanUtils.copyProperties(source, dest);
        BeanUtils.copyProperties(dest, source);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String name;
    private Integer age;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Employee {
    private String name;

    private Integer age;
    private String dept;

}
