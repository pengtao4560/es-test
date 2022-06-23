1、查看Mysql 数据库 "ori_data"下所有表的表名、表注释及其数据量
SELECT
TABLE_NAME 表名,TABLE_COMMENT 表注释,TABLE_ROWS 数据量
FROM information_schema.tables
WHERE TABLE_SCHEMA = 'ori_data'
ORDER BY TABLE_NAME;

2. 查询数据库 ‘ori_data’ 下表 ‘accumulation’ 所有字段注释
   SELECT
   COLUMN_NAME 字段名,column_comment 字段注释
   FROM INFORMATION_SCHEMA.Columns
   WHERE table_name='accumulation' AND table_schema='ori_data'

3. 查询数据库 "ori_data" 下所有表的表名、表注释以及对应表字段注释
   SELECT
   a.TABLE_NAME 表名,a.TABLE_COMMENT 表注释,b.COLUMN_NAME 表字段,b.COLUMN_TYPE 字段类型,b.COLUMN_COMMENT
   字段注释
   FROM information_schema.TABLES a,INFORMATION_SCHEMA.Columns b
   WHERE b.TABLE_NAME=a.TABLE_NAME AND a.TABLE_SCHEMA='ori_data'

information_schema数据库是MySQL数据库自带的数据库，里面存放的MySQL数据库所有的信息，包括数据表、数据注释、数据表的索引、数据库的权限等等。
————————————————
版权声明：本文为CSDN博主「yslhk1982」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
[](https://blog.csdn.net/yslhk1982/article/details/124116442)
