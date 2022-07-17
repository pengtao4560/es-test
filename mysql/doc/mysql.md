# 前言
## ORM思想 (对象关系映射Object Relational Mapping)体现 

    数据库中的一个表  <---> Java或Python中的一个类 
    表中的一条数据    <---> 类中的一个对象（或实体） 
    表中的一个列     <---> 类中的一个字段、属性(field)

## 表的关联关系
表与表之间的数据记录有关系(relationship)。现实世界中的各种实体以及实体之间的各种联系均用关系模型来表示。

四种：一对一关联(类比一夫一妻)、一对多关联（类比一家多孩）、多对多关联、自我引用
### 一对一关系
两种建表原则：
外键唯一：主表的主键和从表的外键（唯一），形成主外键关系，外键唯一。
外键是主键：主表的主键和从表的主键，形成主外键关系

### 一对多关系
常见实例场景： 客户表和订单表 ， 分类表和商品表 ， 部门表和员工表 ，省表和城市表。
举例：
员工表：编号、姓名、...、所属部门
部门表：编号、名称、简介
一对多建表原则：在从表(多方)创建一个字段，字段作为外键指向主表(一方)的主键

### 多对多关系
要表示多对多关系，必须创建第三个表，该表通常称为 联接表 ，它将多对多关系划分为两个一对多关系。将这两个表的主键都插入到第三个表中
举例：
学生-课程
产品-订单
用户-角色

### 自我引用关系

## mysql 安装、路径、版本
1.查看window mysql 装在哪里。  win+r-taskmgr打开任务管理器- mysql.exe -打开文件所在位置

    D:\software\java\mysql\mysql-8.0.28-winx64\bin
2.查看 mysql 版本
cmd 窗口输入
    mysql --version

[mysql官网](https://www.mysql.com)


# SQL语言分类
在功能上主要分为如下3大类：

**DDL（Data Definition Languages、数据定义语言**），这些语句定义了不同的数据库、表、视图、索引等数据库对象，还可以用来创建、删除、修改数据库和数据表的结构。
主要的语句关键字包括 CREATE 、 DROP 、 ALTER 等。

**DML（Data Manipulation Language、数据操作语言）**，用于添加、删除、更新和查询数据库记录，并检查数据完整性。
主要的语句关键字包括 INSERT 、 DELETE 、 UPDATE 、 SELECT 等。
SELECT是SQL语言的基础，最为重要。

**DCL（Data Control Language、数据控制语言）**，用于定义数据库、表、字段、用户的访问权限和安全级别。
主要的语句关键字包括 GRANT 、 REVOKE 、 COMMIT 、 ROLLBACK 、 SAVEPOINT 等。

~~因为查询语句使用的非常的频繁，所以很多人把查询语句单拎出来一类：DQL（数据查询语言）。
还有单独将 COMMIT 、 ROLLBACK 取出来称为TCL （Transaction Control Language，事务控制语言）。~~


# order by 字段  desc/asc
asc 升序 ascend 的缩写
desc 降序 descend 的缩写

# 分页公式 LIMIT (pageNo-1) * pageSize,pageSize;
LIMIT 可以使用在MySQL、PGSQL、MariaDB、SQLite 等数据库中使用，表示分页。 不能使用在SQL Server、DB2、Oracle！

# 出现笛卡尔积的错误的原因就是缺少了多表连接的条件


#如果查询语句中出现了多个表中都存在的字段，则必须指明此字段所在的表。
SELECT employees.employee_id,departments.department_name,employees.department_id
FROM employees,departments
WHERE employees.`department_id` = departments.department_id;

#建议：从sql优化的角度，建议多表查询时，每个字段前都指明其所在的表。

# 结论：如果有n个表实现多表的查询，则需要至少n-1个连接条件

# union 和  union all
合并查询结果 利用 UNION 关键字，可以给出多条SELECT语句，并将它们的结果组合成单个结果集。合并时，两个表对应的列数和数据类型必须相同，并且相互对应。
各个SELECT语句之间使用UNION或UNION ALL关键字分隔。

**UNION 操作符返回两个查询的结果集的并集，去除重复记录
UNION ALL操作符返回两个查询的结果集的并集。对于两个结果集的重复部分，不去重**

注意：执行UNION ALL语句时所需要的资源比UNION语句少。如果明确知道合并数据后的结果数据不存在重复数据，或者不需要去除重复的数据，
则尽量使用UNION ALL语句，以提高数据查询的效率。

# BENCHMARK()用于测试表达式的执行效率
SELECT BENCHMARK(100000,MD5('mysql'))
FROM DUAL;
