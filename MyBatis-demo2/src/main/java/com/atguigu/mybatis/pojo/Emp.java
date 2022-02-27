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
