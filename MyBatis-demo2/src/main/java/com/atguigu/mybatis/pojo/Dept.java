package com.atguigu.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 部门
 *
 * @author pengtao
 * @createdate 2022/02/27 0027
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dept {

    private Integer did;

    private String deptName;

    private List<Emp> empList;
}
