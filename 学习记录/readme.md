1.springcloud 学习  2021年11月17日

计划  每周一到周五  20:05 - 21:00   
                  21:20-21:50
                  如加班，时间推延1-2小时 最晚22.30开始
      周六-周日
      14:00- 14:56
      15:15-16:00
      16:15-17.00
      
   不在能知，乃在能行
   
进度打卡
    11.19  p7-p10 安装 mybatis插件 选择 Free Mybatis Pligin 安装文本编辑器Sublime Text3
                  安装思维导图 亿图脑图MindMaster
                  问题 文本编辑器打开文件文件名乱码
    11.18  p5-p6  安装截图软件 Snipaste
    11.17  p1 -p4            

总父工程
POM
 project
   Modoule

dependencyMangement  通常会在一个组织或者项目的最顶层 父POM中看到

Maven中的dependencyManagement元素提供了一种管理依赖版本号的方式。
在dependencyManagement元素中声明所依赖的jar包的版本号等信息，
那么所有子项目再次引入此依赖jar包时则无需显式的列出版本号。
Maven会沿着父子层级向上寻找拥有dependencyManagement 元素的项目，
然后使用它指定的版本号。

maven中如何跳过单元测试
