package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author pengtao
 * @createdate 2022/02/27 0027
 */
public interface EmpMapper {

    /**
     * 查询所有的员工信息 别名as 和 驼峰映射
     */
    List<Emp> getAllEmpOld();

    /**
     * 查询所有的员工信息 resultMap
     */
    List<Emp> getAllEmp();

    /**
     * 查询员工以及员工所对应的部门信息
     * @param eid
     * @return
     */
    Emp getEmpAndDept(@Param("eid") Integer eid);

    /**
     * 通过分步查询查询员工以及员工对应的部门信息
     * 分步查询第一步：查询员工信息
     *
     * 分步查询第二步：
     * @see DeptMapper#getDeptAndEmpByStepTwo(int)
     */
    Emp getEmpAndDeptByStepOne(@Param("eid") Integer eid);

    /**
     * 通过分步查询查询部门以及部门对应的员工信息
     * 分步查询第一步：
     * @see DeptMapper#getDeptAndEmpByStepOne(int)
     * 分步查询第二步：(本方法)
     */
    List<Emp> getDeptAndEmpByStepTwo(@Param("did") Integer did);


}
