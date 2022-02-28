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
