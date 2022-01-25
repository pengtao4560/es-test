
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
