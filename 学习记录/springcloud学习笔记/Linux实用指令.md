
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
案例： 按文件名-name 根据名称hello.txt 查找/home目录下的 hello.txt
find /home -name hello.txt
按文件拥有着 根据文件名 查找
find /opt -user nobody

查找整个linux系统下 按大小 查找
find / -size +20M
find /home -size -20M
