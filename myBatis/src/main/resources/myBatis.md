## MyBatis 3.5.7 尚硅谷

简介
#### MyBatis历史

MyBatis最初是Apache的一个开源项目iBatis, 2010年6月这个项目由Apache Software Foundation迁移到了Google Code。随着开发团队转投Google Code旗下，iBatis3.x正式更名为MyBatis。代码于2013年11月迁移到Github。
iBatis一词来源于“internet'和“abatis"的组合，是一个基于Java的持久层框架。iBatis提供的持久层框架包括SQL Maps和Data Access Objects (DAO)。

#### MyBatis特性

1) MyBatis是支持定制化SQL、存储过程以及高级映射的优秀的持久层框架
2) MyBatis避免了几乎所有的JDBC代码和手动设置参数以及获取结果集
3) MyBatis可以使用简单的XML或注解用于配置和原始映射，将接口和Java的POJO (Plain Old JavaObjects，
   普通的Java对象）映射成数据库中的记录
4) MyBatis是一个半自动的ORM(Object Relation Mapping 对象关系映射）框架

#### MyBatis下载
   [MyBatis下载地址](https://github.com/mybatis/mybatis-3)

#### MyBatis优点：

轻量级，性能出色
SQL 和 Java 编码分开，功能边界清晰。Java代码专注业务、SQL语句专注数据
开发效率稍逊于HIbernate，但是完全能够接受


#### 创建MyBatis的核心配置文件
习惯上命名为mybatis-config.xml，这个文件名仅仅只是建议，并非强制要求。将来整合Spring
之后，这个配置文件可以省略，所以大家操作时可以直接复制、粘贴。
核心配置文件主要用于配置连接数据库的环境以及MyBatis的全局配置信息

[mybatis-官方文档-中文](https://mybatis.org/mybatis-3/zh/getting-started.html)


char类型不设置长度默认是1.适合 性别等自断

#### 5. 创建 MyBatis 的映射文件

相关概念: ORM (Object Relationship Mapping）对象关系映射。

· 对象:Java的实体类对象
·关系:关系型数据库
·映射:二者之间的对应关系三三

| Java 概念 | 数据库概念 |
|------|-------|
| 类    | 表     |
| 属性   | 字段/列  |
| 对象   | 记录/行  |


1、映射文件的命名规则:
表所对应的实体类的类名+Mapper.xml
例如:表t_user，映射的实体类为User，所对应的映射文件为UserMapper.xml因此一个映射文件对应一个实体类，对应—张表的操作
MyBatis映射文件用于编写SQL，访问以及操作表中的数据
MyBatis映射文件存放的位置是src/main/resources/mappers目录下2、MyBatis中可以面向接口操作数据，要保证两个—致:
a>mapper接口的全类名和映射文件的命名空间(namespace)保持—致b>mapper接口中方法的方法名和映射文件中编写SQL的标签的id属性保持—致



MyBatis面向接口编程的两个一致:
*1、映射文件的namespace要和mapper接口的全类名保持一致
*2、映射文件中soL语句的id要和mapper接口中的方法名一致*/

```xml
<!-- log4j日志 -->
<dependency>
   <groupId>log4j</groupId>
   <artifactId>log4j</artifactId>
   <version>1.2.17</version>
</dependency>
```

```java
package com.atguigu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * mybatis 尚硅谷3.5.7课程学习实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

   private Integer id;

   private String username;

   private String password;

   private Integer age;

   private String sex;

   private String email;

}

```

```java
package com.atguigu.mapper;


import com.atguigu.pojo.User;

import java.util.List;

/**
* @see com.atguigu.mapper.UserMapper
*/
public interface UserMapper {

    /**
     * MyBatis面向接口编程的两个一致：
     * 1、映射文件的namespace要和mapper接口的全类名保持一致
     * 2、映射文件中SQL语句的id要和mapper接口中的方法名一致
     *
     * 表--实体类--mapper接口--映射文件
     */

    /**
     * 添加用户信息
     */
    int insertUser();

    /**
     * 修改用户信息
     */
    void updateUser();

    /**
     * 删除用户信息
     */
    void deleteUser();

    /**
     * 根据id查询用户信息
     */
    User getUserById();

    /**
     * 查询所有的用户信息
     */
    List<User> getAllUser();

}

```


```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.mapper.UserMapper">

   <!-- void updateUser();-->
   <update id="updateUser">

      update
      t_user
      set
      username = "张三"
      where
      id = "3"


   </update>
   <delete id="deleteUser">
      delete from
      t_user
      where
      id = 5

   </delete>

   <!--int insertUser();-->
   <insert id="insertUser" useGeneratedKeys="true">
      INSERT INTO
      t_user
      VALUES
      (NULL,
      'admin',
      '123456',
      23,
      '男',
      '12345@qq.com')

   </insert>

   <!--User getUserById();-->
   <!--
       查询功能的标签必须设置resultType或resultMap
       resultType：设置默认的映射关系
       resultMap：设置自定义的映射关系
   -->
   <select id="getUserById" resultType="com.atguigu.pojo.User">
      select
      *
      from
      t_user
      where
      id = 3
   </select>

   <!--List<User> getAllUser();-->
   <select id="getAllUser" resultType="com.atguigu.pojo.User">
      select
      *
      from
      t_user
   </select>

</mapper>


```

```java
package com.atguigu.test;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * MyBatis插入
 * @see com.atguigu.test.MybatisCRUDDemoTest
 * @author pengtao
 * @createdate 2022/02/27 0027
 */
@Slf4j
public class MybatisCRUDDemoTest {

   private static SqlSession sqlSession;

   static {
      try {
         sqlSession = getSqlSession();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * 获取 SqlSession
    * */
   public static SqlSession getSqlSession() throws IOException {
      // 加载核心配置文件
      InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
      // 获取 SqlSessionFactoryBuilder 对象 - 建造者模式
      SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

      // 获取 SqlSessionFactory 对象 - 工厂模式
      SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
      // 获取 SqlSession sql会话 java对象和数据库表之间的会话
      SqlSession sqlSession = sqlSessionFactory.openSession(true);

      return sqlSession;
   }

   @Test
   public void testInsert() throws IOException {

      // 获取mapper 接口对象 - 代理模式
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      // 测试功能
      mapper.insertUser();
      mapper.updateUser();
      mapper.deleteUser();
        /*User user = mapper.getUserById();
        System.out.println(user);*/
      List<User> list = mapper.getAllUser();
      list.forEach(user -> System.out.println(user));
   }
}

```

测试执行：

      13:32:45.901 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@512baff6]
      13:32:45.903 [main] DEBUG com.atguigu.mapper.UserMapper.insertUser - ==>  Preparing: INSERT INTO t_user VALUES (NULL, 'admin', '123456', 23, '男', '12345@qq.com')
      13:32:45.933 [main] DEBUG com.atguigu.mapper.UserMapper.insertUser - ==> Parameters:
      13:32:45.939 [main] DEBUG com.atguigu.mapper.UserMapper.insertUser - <==    Updates: 1
      13:32:45.939 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Committing JDBC Connection [com.mysql.jdbc.JDBC4Connection@512baff6]
      13:32:45.971 [main] INFO com.atguigu.test.MybatisCRUDDemoTest - insert count: 1

#### resultType 和 resultMap
    <!--
        查询功能的标签必须设置resultType或resultMap
        resultType：设置默认的映射关系
        resultMap：设置自定义的映射关系
    -->

[](mybatis-config-bak.xml)

resources 包下 new-> resource bundle -> 命名为:jdbc.properties

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/mybatis
jdbc.username=root
jdbc.password=root

```
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="jdbc.properties"></properties>
<!--  environments :配置多个连接数据库的环境属性:default:设置默认使用的环境的id-->
    <environments default="development">
<!--
    environment 配置某个具体的环境
        属性有：1. id表示连接数据库的环境的唯一标识，不能重复
                2. transactionManager：设置事务管理方式  属性 type="JDBC/MANAGED"
                    JDBC:表示当前环境中，执行sQL时，使用的是JDBC中原生的事务管理方式。事务的提交或回滚需要手动处理
                    MANAGED(被管理):被管理，例如被spring管理

        -->
        <environment id="development">
            <transactionManager type="JDBC"/>
            <!--
                     dataSource：配置数据源
                     属性：
                         type：设置数据源的类型
                         type="POOLED|UNPOOLED|JNDI"
                         POOLED：表示使用数据库连接池缓存数据库连接
                         UNPOOLED：表示不使用数据库连接池
                         JNDI：表示使用上下文中的数据源
                 -->
            <dataSource type="POOLED">
                <!--设置连接数据库的驱动-->
                <property name="driver" value="${jdbc.driver}"/>
                <!--设置连接数据库的连接地址-->
                <property name="url" value="${jdbc.url}"/>
                <!--设置连接数据库的用户名-->
                <property name="username" value="${jdbc.username}"/>
                <!--设置连接数据库的密码-->
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>

        <environment id="test">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://127.0.0.1:3306/mybatis"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
<!-- 引入映射文件-->
    <mappers>
        <mapper resource="mappers/UserMapper.xml"/>
    </mappers>
</configuration>

```
#### typeAlias

  <!--
            typeAlias：设置某个类型的别名
            属性：
                type：设置需要设置别名的类型
                alias：设置某个类型的别名，若不设置该属性，那么该类型拥有默认的别名，即类名且不区分大小写
        -->
        <!--<typeAlias type="com.atguigu.mybatis.pojo.User"></typeAlias>-->
        <!--以包为单位，将包下所有的类型设置默认的类型别名，即类名且不区分大小写-->
        <typeAlias type="com.atguigu.pojo.User" alias="User"></typeAlias>

TODO 18节课没弄懂

#### MyBatis 获取参数值的两种方式

MyBatis获取参数值的两种方式：${}和#{}

**${}的本质就是字符串拼接，#{}的本质就是占位符赋值**

${}使用字符串拼接的方式拼接sql，若为字符串类型或日期类型的字段进行赋值时，需要手动加单引号；

但是 #{} 使用占位符赋值的方式拼接sql，此时为字符串类型或日期类型的字段进行赋值时，可以自动添加单引号
能用 #{} 的，就不要使用 ${}

各种具体情况说明：

      MyBatis获取参数值的两种方式：${}和#{}
      ${}本质字符串拼接
      #{}本质占位符赋值
      MyBatis获取参数值的各种情况：
      1、mapper 接口方法的参数为单个的字面量类型
      可以通过${}和#{}以任意的名称获取参数值，但是需要注意${}的单引号问题

      2、mapper 接口方法的参数为多个时
      此时MyBatis会将这些参数放在一个map集合中，以两种方式进行存储

      a>以 arg0,arg1...为键，以参数为值
      b>以 param1,param2...为键，以参数为值

      因此只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题

      3、若mapper接口方法的参数有多个时，可以手动将这些参数放在一个map中存储
      只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题

      4、mapper接口方法的参数是 【实体类】 类型的参数
      只需要通过#{}和${}以属性的方式访问属性值即可，但是需要注意${}的单引号问题

      5、使用@Param注解命名参数
      此时MyBatis会将这些参数放在一个map集合中，以两种方式进行存储

      a>以 @Param 注解的值为键，以参数为值
      b>以 param1, param2...为键，以参数为值

      因此只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题

#### IDEA 创建核心配置文件的模板

File | Settings | Editor | File and Code Templates
点击+ 添加 extension 改为xml, name起一个名字


```java
/**
 * @see org.apache.ibatis.reflection.ParamNameResolver.ParamNameResolver
 * 136行
 * 找一个查询，打断点进入调试，即可查看 @Param 注解原理 
    TODO P29先跳过
 */
```

判断类型用 instanceOf 关键字



