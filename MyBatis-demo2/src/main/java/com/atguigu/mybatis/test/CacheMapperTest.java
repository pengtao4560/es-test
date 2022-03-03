package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.CacheMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis缓存测试
 *
 * @author pengtao
 * @createdate 2022/02/28 0028
 */
public class CacheMapperTest {

    @Test
    public void testOneCacheGetEmp() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        CacheMapper mapper2 = sqlSession.getMapper(CacheMapper.class);
        Emp emp = mapper.getEmpByEid(3);
        Emp emp1 = mapper.getEmpByEid(3);
        Emp emp2 = mapper2.getEmpByEid(3);
        System.out.println("emp: " + emp);
        System.out.println("emp1: " + emp1);

        System.out.println("emp2: " + emp2);
        /**
         22:14:30.991 [main] DEBUG com.atguigu.mybatis.mapper.CacheMapper.getEmpByEid - ==>  Preparing: select * from t_emp where eid = ? limit 1;
         22:14:31.037 [main] DEBUG com.atguigu.mybatis.mapper.CacheMapper.getEmpByEid - ==> Parameters: 3(Integer)
         22:14:31.056 [main] DEBUG com.atguigu.mybatis.mapper.CacheMapper.getEmpByEid - <==      Total: 1
         Emp(eid=3, empName=王五, age=12, gender=男, email=123@qq.com, dept=null)
         Emp(eid=3, empName=王五, age=12, gender=男, email=123@qq.com, dept=null)
         Emp(eid=3, empName=王五, age=12, gender=男, email=123@qq.com, dept=null)
         * */
        // 一级缓存默认开启，级别是sqlSession级别的 可证明
        System.out.println("=================");
        SqlSession sqlSession2 = SqlSessionUtils.getSqlSession();
        CacheMapper mapper3 = sqlSession2.getMapper(CacheMapper.class);
        System.out.println("emp3: " + mapper3.getEmpByEid(3));

    }
    @Test
    public void testTwoCache(){

        // SqlSession sqlSession = SqlSessionUtils.getSqlSession();

        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
            CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);

            Emp empByEid1 = mapper1.getEmpByEid(1);
            sqlSession1.close();
            Emp empByEid2 = mapper2.getEmpByEid(1);
            sqlSession2.close();
            System.out.println(empByEid1);
            System.out.println(empByEid2);
            // 必须等到sqlSession关闭或commit之后 二级缓存才有效果


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
