package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

/**
 * mybatis缓存
 *
 * @author pengtao
 * @createdate 2022/02/28 0028
 */
public interface CacheMapper {

    Emp getEmpByEid(@Param("eid") Integer eid);


}
