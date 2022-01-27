
1. vi  正常模式/编辑模式/命令行模式(底行模式)
   1. vi   输入行号 shift+g 跳转行号   gg跳转第一行  G跳转伟行
   2. yy复制，  p粘贴  u撤销  dd删行
2.切换用户
   su - 切换用户名
   例如： su - root
切换到指定运行级别的指令
基本语法
init[012356]
vim /etc/inittab

# Default runlevel. The runlevels used are:
#   0 - halt (Do NOT set initdefault to this)
#   1 - Single user mode
#   2 - Multiuser, without NFS (The same as 3, if you do not have networking)
#   3 - Full multiuser mode
#   4 - unused
#   5 - X11
#   6 - reboot (Do NOT set initdefault to this)
linux如何修改root密码(前提，不能用 远程连接，相当于你接触这台虚拟机所在的电脑， 所以安全性 不会降低)
开机  > 在引导时输入 回车键 - > 看到一个界面输入 -> 看到一个新的界面，选择第二行(editor Kernel编辑内核) 再输入e -> 
- 在这行输入1 然后再输入回车键 -> 再次输入b, 然后将进入单用户模式。 
此时，我们可以进入单用户模式，使用passwd命令修改root密码。 1  

帮助指令 man/help
    main ls
    help cd

pwd指令：  Print the name of the current working directory. 显示当前目录的绝对路径

ls指令： ls - list directory contents
ls -l

cd指令  Change the shell working directory. 切换目录

绝对路径：             例如  cd /home
相对路径：         从当前工作目录开始定位到需要的目录去 例如现在 pwd 显示在  /bin 下。  然后 cd ../home
使用相对路需要知道当前在哪个路径下才能写出这个指令


mkdir指令： 创建目录                 mkdir - make directories
一次创建多级目录，带上 -p参数         mkdir -p /home/animal/tiger
                  -p意思为： no error if existing, make parent directories as needed
                            如果存在（错误）的话，不报错，根据需要创建父目录
rmdir删除目录   - remove empty directories
如果需要删除非空目录，需要使用  rm -rf 指定目录

touch指令 创建空文件
   touch 文件名称
   touch hello.txt hello2.txt

拷贝指令 cp - copy files and directories
cp [选项] source dest
cp [选项] 源文件 拷贝的目录
  常用选项 -r: 递归复制整个文件夹
   -R, -r, --recursive 递归 adj.
   copy directories recursively  递归地 adv.
            # 准确定位源目录和目标目录

rm指令  移除文件或目录 rm - remove files or directories
      -r 递归删除整个文件夹
      -f 强制删除，不提示
               -f, --force
               ignore nonexistent files, never prompt

mv指令 mv - move (rename) files   移动文件与目录或重命名

移动(如果有则重命名)


cat指令-已只读方式查看文件内容 cat - concatenate files and print on the standard output
    -n 显示行号
                        |more 管道符
      cat -n /etc/profile |more    使用空格 翻页  

more指令： 一个基于VI编辑器的文本过滤器，它以全屏幕的方式按页显示文本文件的内容
   more /etc/profile    空格/ctrl + f 翻页  enter 下一行 q退出more模式  看上一页 ctrl+b  

less指令：less - opposite of more 用来分屏查看文件内容，比more指令更强大支持各种显示终端，并不是一次性将整个文件加载之后才显示，
而是根据显示需要加载内容
对于大型文件的显示具有较高效率
         空格/PgUp 翻页/下一页  enter 下一行 q退出more模式  上一页 PgDn   /字符串  向下搜寻字符串  n：向下查找 N：向上查找
                                                                      ?字符串 向上搜寻字符串  n：向下查找 N：向上查找
echo指令
echo输出内容到控制台
    输出环境变量
[root@pengtao ~]# echo $PATH
/usr/lib64/qt-3.3/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin

head指令
    显示文件的开头部分内容，默认文件的前10行
    head -n 5 文件路径文件名 制定显示文件的多少行
    例如

[root@pengtao /]# head -n 5 /etc/profile
# /etc/profile

# System wide environment and startup programs, for login setup
# Functions and aliases go in /etc/bashrc

[root@pengtao /]# 

tail指令
 tail用于输出文件尾部的内容，默认后10行
 tail -n 5 文件  查看文件后5行的文件内容，5可以是任何行数
 tail -f 文件    实时追踪该文档的所有更新
    查看文件最后5行并且实时追踪该文档的更新

[root@pengtao /]# tail -5f /etc/profile
fi
done

unset i
unset -f pathmunge

ln指令
软链接也叫符号链接，类似于windows的快捷方式，主要存放了链接其他文件的路径
例如：

ln -s [源文件或目录][软链接名]

history指令
显示所有执行过的指令 history
显示最近执行过的10个指令 histtory 10
执行历史编号为5的指令 history查看编号    !编号

时间日期类：
date "+%Y-%m-%d %H:%M:%S"
显示日期时间
[root@pengtao /]# date "+%Y-%m-%d %H:%M:%S"
2022-01-25 22:49:25
[root@pengtao /]# 

date指令：显示当前日期
date            
date "+%Y-%m-%d%H:%M:%S"

        [root@localhost /]# date
        Tue Jan 25 11:07:12 PST 2022
        [root@localhost /]# date "+%Y-%m-%d%H:%M:%S"
        2022-01-2511:07:15

date -s "2022-01-25 11:28:55"

cal指令： 显示日历
cal
cal 2022

设置系统时间
date -s "2022-01-25 22:50:45"

cal指令   calendarr日历指令

cal
cal 2022
 
find指令

find [搜索范围] [选项]
案例： 
1.按文件名-name 根据名称hello.txt 查找/home目录下的 文件hello.txt
find /home -name hello.txt
2.按文件拥有者 根据文件名 查找
find /opt -user nobody

3.查找整个linux系统下 按大小 查找  + 大于 -小于 等于直接写 20M
find /usr -size +20M
find /home -size -20k
find / -size 20M
4.查询 / 目录下所有txt文件
find / name *.txt
1M = 1024k

locate指令：  快速定位文件路径
locate指令基于数据库进行查询，第一次运行该指令前，必须使用 updatedb指令创建locate数据库
[root@pengtao mysql]# clear
[root@pengtao mysql]# updatedb
[root@pengtao mysql]# locate hello.txt
/home/hello.txt
[root@pengtao mysql]# 



grep指令和管道符号 |
grep过滤查找， 管道符 “|” 镖师将前一个命令的处理结果输出传递给后面的命令进行处理
 -n 显示匹配行及行号
 -i 忽略字母大小写

案例：
[root@pengtao mysql]# cat /home/hello.txt
public class OrderFeignMain80 {

    public static void main(String[] args) {
        System.out.println("hello world");
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
[root@pengtao mysql]# cat /home/hello.txt | grep world
System.out.println("hello world");
[root@pengtao mysql]# cat /home/hello.txt | grep -n world
4:        System.out.println("hello world");
[root@pengtao mysql]#

压缩和解压缩指令
gzip/gunzip
gzip 压缩文件（*.gz文件 压缩后原文件不保留）   
gunzip 解压缩文件

[root@pengtao home]# ls
animal  a.txt  c  c.txt  hello.txt  
[root@pengtao home]# gzip hello.txt c.txt
[root@pengtao home]# ls
animal  a.txt  c  c.txt.gz  hello.txt.gz 
[root@pengtao home]# gunzip hello.txt.gz
[root@pengtao home]# ls
animal  a.txt  c  c.txt.gz  hello.txt 
[root@pengtao home]#

zip/unzip
zip 压缩文件/ unzip解压缩文件
 zip [选项]xxx.zip 将要压缩的内容
 unzip [选项] 

将home目录下所有文件压缩成home.zip
zip -r home.zip /home/

zip -r home.zip /home/

[root@pengtao home]# unzip -d /opt/tmp/ home.zip
[root@pengtao home]# ll /opt/tmp/ /opt/tmp/home
/opt/tmp/:
总用量 4
drwxr-xr-x. 7 root root 4096 1月  26 20:19 home

/opt/tmp/home:
总用量 36
drwxr-xr-x.  3 root root 4096 1月  25 04:24 animal
-rw-r--r--.  1 root root  874 1月  25 06:28 a.txt
-rw-r--r--.  1 root root   12 1月  25 06:31 c
-rw-r--r--.  1 root root  436 1月  25 06:34 c.txt.gz
-rw-r--r--.  1 root root  190 1月  26 20:07 hello.txt
drwx------. 25 root root 4096 1月  25 03:06 pengtao
drwx------.  4 root root 4096 1月  23 23:35 pt
drwx------.  4 root root 4096 1月  23 23:44 yangxiao
drwx------.  4 root root 4096 1月  23 23:43 zhangwuji


tar指令 tar指令是打包指令，最后打包后的文件是 .tar.gz的文件
tar [选项] XXX.tar.gz 打包的内容
    -c 产生.tar打包文件
    -x 产生详细信息       （--extract, --get extract files from an archive 从存档文件中提取文件，即产生详细信息）
    -z 打包同时压缩
    -x 解压.tar文件

tar -zcvf a.tar.gz hello.txt a.txt
tar -zcvf all.tar.gz /home/            打包整个home下所有文件
解压到当前目录:    
tar -zxvf a.tar.gz
解压到指定目录  -C 
tar -zvf a.tar.gz -C /opt/tmp/
[root@pengtao home]# tar -zcvf a.tar.gz hello.txt a.txt
hello.txt
a.txt
[root@pengtao home]# ls
animal  a.tar.gz  a.txt  c  c.txt.gz  hello.txt  home.zip  pengtao  pt  yangxiao  zhangwuji
[root@pengtao home]#

权限管理(文件和目录的权限)

本段需要参考 [](linux目录权限相关说明.md) 

chmod指令
r:read  w:write  x:execute    
u:root   g:group  o:other a:all
[root@pengtao home]# chmod u=rwx,g=rw,o=r hello.txt
[root@pengtao home]# ls -lh hello.txt
-rwxrw-r--. 1 root root 190 1月  26 20:07 hello.txt

chown修改文件所有者
chown newowner file

使用root目录进行操作：
将 /usr/local/mysql 目录 所有的文件和目录的 所有者 都改成 mysql
chown -R mysql /usr/local/mysql
-R 如果是目录，则使其下所有子文件或目录递归生效

chgrp修改文件所在组
chown group file
chgrp -R /home/pengtao pengtao


[root@pengtao pt]# chown -R  pengtao /home/pt
[root@pengtao pt]# ll -s /home/pt
总用量 0
0 -rw-r--r--. 1 pengtao root 0 1月  26 21:31 hello.txt
[root@pengtao pt]# chgrp -R wudang /home/pt
chgrp: 无效的组："wudang"
[root@pengtao pt]# chgrp -R mojiao /home/pt
[root@pengtao pt]# ll -s /home/pt
总用量 0
0 -rw-r--r--. 1 pengtao mojiao 0 1月  26 21:31 hello.txt
[root@pengtao pt]#


crond 任务调度
croutab 进行任务调度
基本语法：

    crontab [选项]
     -e 编辑crontab定时任务
     -l 查看crontab任务
     -r 删除当前用户所有的ctrontab任务

快速入门案例：
设置任务调度文件: /etc/crontab
设置个人任务调度
执行crontab -e 命令。 
然后输入任务到调度文件，
例如:
*/1 * * * * ls -l /etc >> /tmp/to.txt

每小时的每分钟执行 ls -l /etc/ > /tmp/to.txt 命令
5个占位符的说明：



【1.如果只是简单的任务，可以不用写脚本，直接在crontab中加入任务即可
 2. 对于比较复杂的任务，需要写脚本（shell 编程）】
 3. 
以下百度即可：
 
    第1列 分钟1～59
    第2列 小时1～23（0表示子夜）
    第3列 日1～31
    第4列 月1～12
    第5列 星期0～6（0表示星期天）
    第6列 要运行的命令

    * 表示任何时间，比如第一个*代表一个小时中每分钟都执行一次

    , 代表不连续的时间，如"0 8,12,16 * * *"代表每天8点0分、12点0分、16点0分执行一次命令

    - 代表连续的时间范围，如"0 5 * * 1-6"代表每周一至周六的凌晨5点0分执行命令


    */n 代表每隔多久执行一次。如上述示例中代表每隔一分钟执行一次命令

案例参考 96页 [](https://github.com/pengtao4560/cloud2020/blob/6d75223cd271e782113c0d5b9e6f107bba79e0ab/%E5%AD%A6%E4%B9%A0%E8%AE%B0%E5%BD%95/pdf)

1、编写shell脚本，如
vim /home/mytask.sh

date >> /tmp/mydate

2、给mytask.sh一个可执行的权限

chmod 744 /home/mytask.sh

3、crontab -e

    crond相关指令：
        crontab -e 编辑任务
        crontab -r 终止任务调度
        crontab -l 列出当前有哪些任务调度
        service crond restart 重启任务调度

*/1 * * * * /home/mytask.sh
// TODO 

Linux磁盘分区、挂载
分区基本知识：了解 mbr分区  
windows下磁盘分区：
Linux磁盘分区 

            mount挂载
            umount卸载
老师不离开指令lsblk： 查看系统的分区和挂载的情况

    [root@pengtao home]# lsblk -f
    NAME   FSTYPE  LABEL            UUID                                 MOUNTPOINT
    sr0    iso9660 CentOS_6.8_Final                                      /media/CentOS_6.8_Final
    sda                                                                  
    ├─sda1 ext4                     16783e93-0db1-4f38-8861-0eeb058c3fab /boot
    ├─sda2 ext4                     81715f87-a9dd-4ba7-9af7-5a7058ea8f76 /
    └─sda3 swap                     150bc620-7328-4952-9543-5a0ba183bc13 [SWAP]
    [root@pengtao home]#
    分区情况 分区类型                唯一标识分区的40位不重复的字符串         挂载点



df指令  报告文件系统磁盘空间使用情况 report file system disk space usage
df -lh

du指令： 查询指定目录的磁盘占用情况 estimate file space usage
du -h /目录

 -s 指定目录占用大小汇总
 -h 带计量单位
 -a all 包含文件
 --max-depth=1 子目录深度
 -c 列出明细的同事，增加汇总量

案例：
du -ach --max-depth=1 /opt

    [root@pengtao ~]# du -ach --max-depth=1 /opt
    163M	/opt/vmware-tools-distrib
    4.0K	/opt/rh
    54M	/opt/VMwareTools-10.3.10-13959562.tar.gz
    1008K	/opt/tmp
    217M	/opt
    217M	总用量
    [root@pengtao ~]# 

指令

    1)统计/home文件夹下文件的个数       wc统计个数
    ls -l /home |grep "^-" | wc -l
    
    2)统计/home文件夹下目录的个数
    ls -l /home |grep "^d" | wc -l
    
    3)统计/home文件夹下文件的个数， 包括子文件夹里的
    ls -lR /home |grep "^-" | wc -l
    
    4)统计文件夹下目录的个数，包括子文件夹里的
    ls -lR /home |grep "^d" | wc -l
    
    5)以树状显示目录结构  
    tree
    yum install tree
    tree
1.虚拟机能否连接外网
2. DNS配置是否有问题！检验DNS配置是否正常可以这样做：nslookup www.baidu.com
3. centos6 不支持yum解决：
[参考博客](https://www.xmpan.com/944.html)
centos6 不支持yum 一键复制解决：
sed -i "s|enabled=1|enabled=0|g" /etc/yum/pluginconf.d/fastestmirror.conf
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
curl -o /etc/yum.repos.d/CentOS-Base.repo https://www.xmpan.com/Centos-6-Vault-Aliyun.repo
yum clean all
yum makecache


Linux网络配置：
目前我们的网络配置采用的是NAT模式
一。自动ip
linux-系统-首选项-网络连接-编辑-自动连接-应用
缺点：每次自动获取的IP地址可能不一样。如果是个网站，每次IP地址不一样是不行的，不适用于做服务器。
服务器的IP是需要固定的
二 指定固定的ip

vi /etc/sysconfig/network-scripts/ifcfg-eth0
追加：
        IPADDR=192.168.159.131
        GATEWAY=192.168.159.2
        DNS1=192.168.159.2
        PREFIX=24
找到BOOTPROTO修改为：
        BOOTPROTO=static
确认      ONBOOT=yes
