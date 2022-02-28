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


char类型不设置长度默认是1.适合 性别等字段

#### 5. 创建 MyBatis 的映射文件

相关概念: ORM (Object Relationship Mapping）对象关系映射。

· 对象:Java的实体类对象 

·关系:关系型数据库

·映射:二者之间的对应关系

| Java 概念 | 数据库概念 |
|------|-------|
| 类    | 表     |
| 属性   | 字段/列  |
| 对象   | 记录/行  |


1、映射文件的命名规则:
表所对应的实体类的类名+Mapper.xml
例如:表t_user，映射的实体类为User，所对应的映射文件为UserMapper.xml因此一个映射文件对应一个实体类，对应—张表的操作
MyBatis映射文件用于编写SQL，访问以及操作表中的数据
MyBatis映射文件存放的位置是src/main/resources/mappers目录下

2、MyBatis中可以面向接口操作数据，要保证两个—致:

a>mapper接口的全类名和映射文件的命名空间(namespace)保持—致

b>mapper接口中方法的方法名和映射文件中编写SQL的标签的id属性保持—致



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



#### 六 yBatis 的各种查询功能

   MyBatis的各种查询功能：
   1、若查询出的数据只有一条
      a>可以通过实体类对象接收
      b>可以通过list集合接收
      c>可以通过map集合接收
   结果：{password=123456, sex=男, id=3, age=23, email=12345@qq.com, username=admin}
   2、若查询出的数据有多条
      a>可以通过实体类类型的list集合接收
      b>可以通过map类型的list集合接收
      c>可以在mapper接口的方法上添加 @MapKey 注解，此时就可以将每条数据转换的map集合作为值，以某个字段的值作为键，放在同一个map集合中
      注意：一定不能通过实体类对象接收，此时会抛异常TooManyResultsException
  
   MyBatis中设置了默认的类型别名
   java.lang.Integer-->int,integer
   int-->_int,_integer
   Map-->map
   String-->string

```java
package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 */
public interface SelectMapper {

    /**
     * 根据id查询用户信息
     */
    List<User> getUserById(@Param("id") Integer id);

    /**
     * 查询所有的用户信息
     */
    List<User> getAllUser();

    /**
     * 查询用户信息的总记录数
     */
    Integer getCount();

    /**
     * 根据id查询用户信息为一个map集合
     */
    Map<String, Object> getUserByIdToMap(@Param("id") Integer id);

    /**
     * 查询所有用户信息为map集合
     */
    //List<Map<String, Object>> getAllUserToMap();
    @MapKey("id")
    Map<String, Object> getAllUserToMap();

}

```

#### 特殊 SQL 的执行

模糊查询

```java
package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Date:2021/11/29
 * Author:ybc
 * Description:
 */
public interface SQLMapper {

   /**
    * 根据用户名模糊查询用户信息
    */
   List<User> getUserByLike(@Param("username") String username);

   /**
    * 批量删除
    */
   int deleteMore(@Param("ids") String ids);

   /**
    * 查询指定表中的数据
    */
   List<User> getUserByTableName(@Param("tableName") String tableName);

   /**
    * 添加用户信息
    */
   void insertUser(User user);

}

```

#### 添加功能获取自增主键： useGeneratedKeys keyProperty
        useGeneratedKeys:设置当前标签中的sql使用了自增的主键
        keyProperty:将自增的主键的值赋值给传输到映射文件中参数的某个属性

#### 自定义映射 resultMap

mapUnderscoreToCamelCase : 将下划线自动映射成驼峰


    
      解决字段名和属性名不一致的情况：

      1>为字段起别名，保持和属性名的一致

      2>设置全局配置，将_自动映射为驼峰
      <setting name="mapUnderscoreToCamelCase" value="true"/>

      3>通过resultMap设置自定义的映射关系

```xml
 <resultMap id="empResultMap" type="Emp">
          <id property="eid" column="eid"></id>
          <result property="empName" column="emp_name"></result>
          <result property="age" column="age"></result>
          <result property="gender" column="gender"></result>
          <result property="email" column="email"></result>
      </resultMap>
      
      <!--  resultMap: 设置自定义映射关系
        type: 设置映射关系中的实体类类型
        子标签：
            id： 设置主键的映射关系
            result: 设置普通字段的映射关系
            属性：
                property: 设置映射关系中的属性名，必须是 type 属性所设置的实体类类型中的属性名
                column : 设置映射关系中的字段名，必须是 sql 语句查询出的字段名
-->
      
<!--      处理多对一的映射关系：-->
<!--      a>级联属性赋值-->
<!--      b>association-->
<!--      c>分步查询-->
<!--     -->
<!--      处理一对多的映射关系-->
<!--      a>collection-->
<!--      b>分步查询-->
```

用resultMap 一般是处理多对一和一对多的映射关系，处理字段名不一致有点浪费

多对一的映射： 在 多的类中 写一个 “一”所对应的对象就可以了 例如员工Emp 中的 dept 属性

一对多的映射： 在 一的类中 写一个多的集合就可以了

```java
package com.atguigu.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 员工
 *
 * @author pengtao
 * @createdate 2022/02/27 0027
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Emp {

    private Integer eid;

    private String empName;

    private Integer age;

    private String gender;

    private String email;

    private Dept dept;
}

```

#### 处理多对一的 映射关系方式：
   1. 级联调属性赋值
   2. association
   3. 分步查询
```java
/**
 * 
 * @see com.atguigu.mybatis.test.ResultMapTest#; 
 */

```
```xml

```

##### 分步查询的优点：
可以实现延迟加载，但是必须在核心配置文件中设置全局配置信息：
lazyLoadingEnabled：延迟加载的全局开关。当开启时，所有关联对象都会延迟加载
aggressiveLazyLoading：当开启时，任何方法的调用都会加载该对象的所有属性。 否则，每个
属性会按需加载
此时就可以实现按需加载，获取的数据是什么，就只会执行相应的sql。此时可通过association和
collection中的fetchType属性设置当前的分步查询是否使用延迟加载，fetchType="lazy(延迟加
载)|eager(立即加载)"

#### 处理一对多的映射关系
   1.Collection
   2. 分步处理


#### 九、动态SQL

Mybatis框架的动态SQL技术是一种根据特定条件动态拼装SQL语句的功能，它存在的意义是为了解决
拼接SQL语句字符串时的痛点问题。

1. if 标签 ：根据标签中 test 属性所对应的表达式决定标签中的内容是否需要拼接到 SQL 中

2. where 标签: 当 where 标签中有内容时，会自动生成 where 关键字，并且将内容前多余的 and 或 or 去掉 
               当 where 标签中没有内容时，此时where标签没有任何效果(不会生成where关键字)
   注意： where 标签不能将其中内容后面多余的and或or去掉

3. trim 标签：若标签中有内容时：
   prifix/suffix属性：将trim标签中内容前面或后面添加指定内容
   prefixOverrides/suffixOverrides: 将trim标签中内容前面或后面的内容去掉
   若标签中没有内容时，trim标签也没有任何效果
4. （用的较少）choose、when、otherwise 相当于 if...else if...else
   当时用了choose 标签，when 至少要有一个， otherwise 标签最多只能有一个

5. foreach
   collection: 设置需要循环的数组或集合
   item: 表示数组或集合中的每一个元素
   separator: 循环体之间的分隔符
   open: foreach 标签所循环的所有内容的开始符
   close: foreach 标签所循环的所有内容的结束符

6. sql标签
   声明、设置 SQL 片段：<sql id="empColumns">eid, emp_name, age, gender, email</sql>
   引用 SQL 片段：<include refid="empColumns"></include>

动态sql案例Demo:

mapper.java
```java
package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 动态SQL
 *
 * @author pengtao
 * @createdate 2022/02/27 0027
 */
public interface DynamicSQLMapper {

    /**
     * 多条件查询
     */
    List<Emp> getEmpCondition(Emp emp);

    /**
     * 测试choose、when、otherwise标签
     */
    List<Emp> getEmpConditionByChoose(Emp emp);

    /**
     * 通过数组实现批量删除
     */
    int deleteMoreByArray(@Param("eids") Integer[] edis);

    /**
     * 通过数组实现批量删除
     *  Preparing: delete from t_emp where eid = ? or eid = ? or eid = ?
     *  Parameters: 7(Integer), 8(Integer), 9(Integer)
     */
    int deleteMoreByArrayTwo(@Param("eids") Integer[] edis);

    /**
     * 通过list集合实现批量添加
     */
    int insertByList(@Param("emps") List<Emp> emps);
}

```
mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.mybatis.mapper.DynamicSQLMapper">

    <insert id="insertByList">
        insert into
            t_emp
        values
            <foreach collection="emps" item="emp" separator=",">
                (null,
                #{emp.empName},
                #{emp.age},
                #{emp.gender},
                #{emp.email},
                null
                )
            </foreach>
    </insert>

    <delete id="deleteMoreByArray">
        delete from
            t_emp
        where
            eid
        in
        <foreach collection="eids" item="eid" separator="," open="(" close=")">
            #{eid}
        </foreach>
    </delete>

    <delete id="deleteMoreByArrayTwo">
        delete from
         t_emp
        where
        <foreach collection="eids" item="eid" separator="or">
            eid = #{eid}
        </foreach>
    </delete>

    <sql id="empColumns">
        eid, emp_name, age, gender, email
    </sql>
    <select id="getEmpCondition" resultType="com.atguigu.mybatis.pojo.Emp">
        select
            <include refid="empColumns"></include>
        from
        t_emp

        <trim prefix="where" suffixOverrides="and | or">
            <if test="empName != null and empName != ''">
                emp_name = #{empName} and
            </if>

            <if test="age != null and age != ''">
                 age = #{age} and
            </if>

            <if test="gender != null and gender != ''">
                 gender = #{gender}
            </if>

            <if test="email != null and email != ''">
                and email = #{email}
            </if>
        </trim>
    </select>

    <select id="getEmpConditionTwoWhere" resultType="com.atguigu.mybatis.pojo.Emp">
        select
            *
        from
            t_emp
       <where>
           <if test="empName != null and empName != ''">
               emp_name = #{empName}
           </if>

           <if test="age != null and age != ''">
               and age = #{age}
           </if>

           <if test="gender != null and gender != ''">
               and gender = #{gender}
           </if>

           <if test="email != null and email != ''">
               and email = #{email}
           </if>
       </where>
    </select>

    <select id="getEmpConditionOneIf" resultType="com.atguigu.mybatis.pojo.Emp">
        select
        *
        from
        t_emp
        where
        1 = 1 and
        <if test="empName != null and empName != ''">
            emp_name = #{empName}
        </if>

        <if test="age != null and age != ''">
            and age = #{age}
        </if>

        <if test="gender != null and gender != ''">
            and gender = #{gender}
        </if>

        <if test="email != null and email != ''">
            and email = #{email}
        </if>
    </select>

    <select id="getEmpConditionByChoose" resultType="com.atguigu.mybatis.pojo.Emp">
        select
            *
        from
            t_emp
        <where>
            <choose>
                <when test="empName != null and empName !='' ">
                    emp_name = #{empName}
                </when>

                <when test="empName != null and empName !='' ">
                    age = #{age}
                </when>

                <when test="gender != null and gender !='' ">
                    gender = #{gender}
                </when>

                <when test="email != null and email !='' ">
                    email = #{email}
                </when>
                <otherwise>
                    did = 1
                </otherwise>
            </choose>
        </where>
    </select>
</mapper>

```

测试类
```java
package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.DynamicSQLMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengtao
 * @createdate 2022/02/27 0027
 */
public class DynamicSQLTest {

    @Test
    public void testGetEmpByCondition() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        mapper.getEmpCondition(new Emp(null, "张三", null, "男", "123!@qq.com", null));
        mapper.getEmpCondition(new Emp(null, "", null, "", "", null));
    }

    @Test
    public void testGetEmpConditionByChoose() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> list = mapper.getEmpConditionByChoose(new Emp(null, "", null, "", "", null));

        System.out.println(list);
        System.out.println();

        List<Emp> list2 = mapper.getEmpConditionByChoose(new Emp(null, "张三", null, "男", "123!@qq.com", null));
        System.out.println(list2);

    }

    @Test
    public void testDeleteMoreByArray() {

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int deleteCount = mapper.deleteMoreByArray(new Integer[]{7, 8, 9});
        System.out.println(deleteCount);
    }

    @Test
    public void testDeleteMoreByArrayTwo() {

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int deleteCount = mapper.deleteMoreByArrayTwo(new Integer[]{7, 8, 9});
        System.out.println(deleteCount);
    }
    @Test
    public void testInsertByList() {

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> list = new ArrayList<>();
        Emp emp = new Emp(null, "张三", null, "男", "123!@qq.com", null);
        list.add(emp);
        list.add(emp);
        list.add(emp);

        int insertCount = mapper.insertByList(list);

        System.out.println(insertCount);
    }
}

```
