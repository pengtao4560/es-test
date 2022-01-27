
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
crontab [选项]
 -e 
