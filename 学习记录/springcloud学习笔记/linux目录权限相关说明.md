[Linux系统对文件及目录的权限管理（chmod、chown）](https://www.cnblogs.com/du-z/p/10845189.html)

本文命令：
ls -l	chmod	chown
1、身份介绍
在linux系统中，对文件或目录来说访问者的身份有三种：
①、属主用户，拥有者（owner）文件的创建者
②、属组用户，和文件的owner同组的用户（group）；
③、其他用户，除了所有者、与所有者同组的用户以及除了超级管理员外系统内的其他用户；
其中：用户组的概念详见：《linux 用户、用户组及相关命令》https://www.cnblogs.com/du-z/p/10840560.html

2、权限介绍
在linux系统中，对文件或目录来说访问者有三种权限：
①、读权限（read） ls 、cat、more、head、tail等命令
②、写权限（write） cp 、mv、rm、touch、mkdir、>>、等命令
③、执行权限（execute） cd 等命令

3、Root用户（超级用户）
在Linux中，还有一个神一样存在的用户，这就是root用户，因为在所有用户中它拥有最大的权限 ，所以管理着普通用户。

4、 ls -l 权限查看
ls -l 命令：长输出查看，可查看文件或目录的权限设置；

╭─root@localhost.localdomain /etc  
╰─➤  ls -lh
总用量 1.4M
drwxr-xr-x.  3 root root    101 4月  18 21:38 abrt
-rw-r--r--.  1 root root     16 4月  18 21:49 adjtime
-rw-r--r--.  1 root root   1.5K 6月   7 2013 aliases
-rw-r--r--.  1 root root    12K 4月  18 22:29 aliases.db
drwxr-xr-x.  2 root root     51 4月  18 21:39 alsa
drwxr-xr-x.  2 root root   4.0K 4月  18 21:44 alternatives
-rw-------.  1 root root    541 3月  31 2016 anacrontab
头十位字符表示含义：
第1位：表示文档类型，取值常见的有“d表示文件夹”、“-表示文件”、“l表示软连接”、“s表示套接字”、“c表示字符设备”、“b表示块状设备”等等；
第2-4位：表示文档属主用户权限
第5-7位：表示属组用户权限
第8-10位：表示other用权限
其中:rwx分别表示读、写、执行权限；没有对应权限就用 – 代替。

5、chmod 权限更改
chmod [option] filename/dirname
注意：执行者必须是属主或root用户；

①、字母形式命令
给谁设置：
u：表示属主owner（user）
g：表示属组（group）
o：表示others，给其他用户设置权限
a：表示all，给所有人（包含ugo部分）设置权限
如果在设置权限的时候不指定给谁设置，则默认给所有用户设置

权限字符：
r：读
w：写
x：执行
-：表示没有权限

权限分配方式：
+：表示给具体的用户新增权限（相对当前）
-：表示删除用户的权限（相对当前）
=：表示将权限设置成具体的值（注重结果）【赋值】

d--------- 2 root root 17 5月  11 11:12 dir2
-----w--w- 1 root root  0 5月  11 10:57 file1
╭─root@localhost.localdomain ~/dir1  
╰─➤  chmod u+x,g=wr,o=wx dir2    #用逗号分隔
╭─root@localhost.localdomain ~/dir1  
╰─➤  ls -lh
总用量 0
d--xrw--wx 2 root root 17 5月  11 11:12 dir2
-----w--w- 1 root root  0 5月  11 10:57 file1
②、数字形式命令
读：r 4
写：w 2
执行：x 1
没有任何权限：0 对应—

例如：需要属主全部权限；属组读加执行权限；other只读权限；
属主权限 = 全部权限 = 读 + 写 +执行 = 4 + 2 + 1 = 7
属组权限 = 读权限 + 执行权限 = 4 + 1 = 5
other用户权限 = 读权限 = 4
最终得出的结果是754

常用选项 -R
-R 递归选项：同时设置目录及目录下的目录和文件的权限，且递归；

╭─root@localhost.localdomain ~  
╰─➤  chmod -R 777 dir1   
╭─root@localhost.localdomain ~  
╰─➤  cd dir1
╭─root@localhost.localdomain ~/dir1  
╰─➤  ls -lh
drwxrwxrwx 2 root root 6 5月  11 10:57 dir2
-rwxrwxrwx 1 root root 0 5月  11 10:57 file1
6、chown （change owner）
作用：改变文件或目录的属主和属组；
chown [-R] newuser dirname
注意:①改文件不用加选项-R，该目录需要加选项-R；
②执行者必须为root；owner身份没有权限（其他人不想你随便把文件归给他）；

chown user:group filename 把文件的属主和属组改为user，group
chown user filename 把文件的属主改为user
chown :group filename 把文件的属组改为group
chown user: filename 自动继承user这个用户所有的组

d--xrw--wx 2 root root 17 5月  11 11:12 dir2
-----w--w- 1 root root  0 5月  11 10:57 file1
╭─root@localhost.localdomain ~/dir1  
╰─➤  chown du: -R dir2
╭─root@localhost.localdomain ~/dir1  
╰─➤  ll
总用量 0
d--xrw--wx 2 du   du   17 5月  11 11:12 dir2
-----w--w- 1 root root  0 5月  11 10:57 file1


