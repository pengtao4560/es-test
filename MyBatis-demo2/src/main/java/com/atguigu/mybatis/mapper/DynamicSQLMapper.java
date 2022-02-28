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
