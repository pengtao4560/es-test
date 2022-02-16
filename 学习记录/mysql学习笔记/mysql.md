###mysql安装步骤

[mysql下载官网](https://dev.mysql.com/downloads/mysql/)

1. 下载

        选择 LINUX GENERIC  一般的linux
        查看linux中  glibc版本。选择版本
        rpm -qa|grep glibc
        点击下载：
        选择 no thanks， just start mydowload,右键复制链接地址
        wget 下载地址
        cd /usr/local
        wget https://dev.mysql.com/get/Downloads/MySQL-8.0/mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar.xz
2. 解压

    【xz文件是一种压缩文件格式       xz -z 压缩/ xz -d 解压缩】
    xz -d 要解压的文件
    tar xvf xxx.tar         #来解.tar包。
    [root@localhost /]# ls
    mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar.xz  
    [root@localhost /]# xz -d mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar.xz
    [root@localhost /]# ls
    mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar
    [root@localhost /]# tar xvf mysql-8.0.28-linux-glibc2.17-x86_64-minimal.tar
3. 改名移动

    mv 解压的文件  /usr/local/mysql
    mv mysql-8.0.28-linux-glibc2.17-x86_64-minimal /usr/local/mysql

3.设置权限
        chown -R mysql:mysql /usr/local/mysql
4.
5.mysql第一次登陆修改root用户密码
        安装好后，拷贝临时密码
        登陆成功后：
        
        alter user root@localhost identified by 'root';
