package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

/**
 * 部门
 *
 * @author pengtao
 * @createdate 2022/02/27 0027
 */
public interface DeptMapper {

    /**
     * 通过分步查询查询员工以及员工对应的部门信息
     * 分步查询第二步：通过did查询员工所对应的部门
     *
     * 分步查询第一步：
     * @see com.atguigu.mybatis.mapper.EmpMapper#getEmpAndDeptByStepOne(java.lang.Integer)
     */
    Dept getDeptAndEmpByStepTwo(@Param("did") int did);

    /**
     * 获取部门以及部门中所有的员工信息
     * @param did
     * @return
     */
    Dept getDeptAndEmp(@Param("did") int did);

    /**
     * 通过分布查询查询部门以及部门中的所有的员工信息
     * 分步查询第一步：查询部门信息（本方法）
     *
     * 分步查询第二步：
     * @see EmpMapper#getDeptAndEmpByStepTwo(java.lang.Integer)
     */

    Dept getDeptAndEmpByStepOne(@Param("did") int did);
}
