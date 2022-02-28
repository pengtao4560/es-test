###mysql安装步骤

1 下载 [mysql下载官网](https://dev.mysql.com/downloads/mysql/)
选择 LINUX GENERIC(generic 一般的通用的)
        
        rpm -qa|grep glibc #查看linux中  glibc版本。选择版本
点击下载：选择 no thanks， just start mydowload,右键复制链接地址。wget 下载地址
        
        cd /usr/local
        wget https://dev.mysql.com/get/Downloads/MySQL-8.0/mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar.xz
2 解压【xz文件是一种压缩文件格式       xz -z 压缩/ xz -d 解压缩】【tar xvf xxx.tar 来解.tar包。】
   
    xz -d mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar.xz  
    tar xvf mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar  #tar xvf xxx.tar 来解.tar包。

    [root@localhost local]# ls
    mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar.xz  
    [root@localhost local]# xz -d mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar.xz
    [root@localhost local]# ls
    mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar
    [root@localhost local]# tar xvf mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar

3 改名移动
mv 解压的文件  /usr/local/mysql

    mv mysql-8.0.28-linux-glibc2.17-x86_64-minimal /usr/local/mysql

4 设置权限。如果出错需要先添加用户和组
        groupadd -r mysql
        useradd -g mysql -r -s /sbin/nologin -M -d /mydata/data mysql
        chown -R mysql:mysql /usr/local/mysql

5 mysql第一次登陆修改root用户密码
        安装好后，拷贝临时密码
        登陆成功后：
        
        alter user root@localhost identified by 'root';


6 重启后无法登陆mysql。需要再次启动服务
sudo ./support-files/mysql.server start  #centos 6
service start mysql  #centos7

    [root@localhost /]# service mysql start
    Starting MySQL..... SUCCESS! 
7. 设置mysql开机自启动
   vim /etc/rc.local
   添加 service mysqld start     

#### reboot重启linux 后登陆mysql报错：ERROR 2002 (HY000): Can't connect to local MySQL server through socket '/tmp/mysql.sock' (2) 解决办法

    1、将服务文件拷贝到init.d下，并重命名为mysql
    
    cp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql
    
    
    2、赋予可执行权限
    
    chmod +x /etc/init.d/mysql
    3、添加服务
    
    chkconfig --add mysql
    4、显示服务列表

    chkconfig --list


    ▲：如果看到mysql的服务如上图所示2,3,4,5都是开的话则成功，默认级别是2345

    如果是关，则键入命令开启

    chkconfig --level 2345 mysql on
    5、重启服务器
    
    reboot
    
    
    命令说明：

    --add：增加所指定的系统服务，让chkconfig指令得以管理它，并同时在系统启动的叙述文件内增加相关数据；
    --del：删除所指定的系统服务，不再由chkconfig指令管理，并同时在系统启动的叙述文件内删除相关数据；
    --level<等级代号>：指定读系统服务要在哪一个执行等级中开启或关毕。
    等级代号列表：
    
    等级0表示：表示关机
    等级1表示：单用户模式
    等级2表示：无网络连接的多用户命令行模式
    等级3表示：有网络连接的多用户命令行模式
    等级4表示：不可用
    等级5表示：带图形界面的多用户模式
    等级6表示：重新启动
    需要说明的是，level选项可以指定要查看的运行级而不一定是当前运行级。对于每个运行级，只能有一个启动脚本或者停止脚本。当切换运行级时，init不会重新启动已经启动的服务，也不会再次去停止已经停止的服务。


#### mysql 8 赋予权限问题解决

update user set host='%' where user='root';

Grant all privileges on *.* to 'root'@'%';

##### 连接Navicat/SQLyog 报错 

原因：mysql8默认的加密方式为caching_sha2_password 与mysql5的加密方式mysql_native_password 不同

修改加密方式：
alter user 用户 identified with mysql_native_password by '密码'；
alter user root identified with mysql_native_password by 'root'；

[mysql8赋予权限问题](https://blog.csdn.net/qq_34680444/article/details/86238516?spm=1001.2101.3001.6650.15&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-15.pc_relevant_paycolumn_v3&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-15.pc_relevant_paycolumn_v3&utm_relevant_index=22)



#### SQL语句的书写顺序:

select * | 列名 -- 确定要查询的列有哪些
from 表名 -- 确定查询哪张表
where 条件 -- 通过筛选过滤, 剔除不符合条件的记录
group by 分组的列 -- 指定根据哪一列进行分组
having 条件 -- 通过条件对分组后的数据进行筛选过滤
order by 排序的列 -- 指定根据哪一列进行排序
limit (countPage-1)*rowCount, rowCount -- 指定返回第几页记录以及每页显示多少条

#### SQL语句的执行顺序:

from 表名 -- 确定查询哪张表
where 条件 -- 通过筛选过滤, 剔除不符合条件的记录
select * | 列名 列别名 -- 确定要查询的列有哪些,
group by 分组的列 -- 指定根据哪一列进行分组
having 条件 -- 通过条件对分组后的数据进行筛选过滤
order by 排序的列 -- 指定根据哪一列进行排序
limit (countPage-1)*rowCount, rowCount


#### 备份数据库 
在cmd窗口中(未登录的状态):
mysqldump –u用户名 –p 备份的数据库的名字 > 备份文件的位置
例如:将db40库中的数据备份到 d:/db40.sql文件中
mysqldump –uroot –p db40 > d:/db40.sql

回车后, 输入密码再回车, 如果没有报错, 则备份成功! 到d盘根目录查看db40.sql文件.
注意, 备份库时, 只是备份了建表和插入数据的sql语句, 并不会备份库本身, 所以当恢复时, 需要自己先创建库, 再进行恢复数据.

##### 恢复数据库
方式一: 在cmd窗口中(未登录的状态):

mysql –u用户名 –p 恢复的数据库的名字 < 备份文件的位置
例如: 将db40库误删了, 现在通过d:/db40.sql文件, 将数据恢复到db80库中:
mysql –uroot –p db80 < d:/db40.sql

方式二: 在cmd窗口中(已登录的状态):

set names gbk; -- 防止乱码
create database db100 charset utf8;
use db100;
source 备份文件的位置;

#### 查看数据库版本
SELECT VERSION();
