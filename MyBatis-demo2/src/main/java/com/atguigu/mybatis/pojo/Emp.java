package com.atguigu.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

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
public class Emp implements Serializable {

    private static final long serialVersionUID = 2518463530069815062L;

    private Integer eid;

    private String empName;

    private Integer age;

    private String gender;

    private String email;

    private Dept dept;
}
