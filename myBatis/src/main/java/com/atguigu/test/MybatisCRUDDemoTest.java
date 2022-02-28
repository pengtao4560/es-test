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
 *
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
    public void testInsert() {

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
