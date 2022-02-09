彭涛学习 哔哩哔哩尚硅谷 springcloud课程、java8新特性、nginx、linux

未来：redis、mongo、消息队列、mysql进阶、


github访问慢解决办法：

1. 打开本地hosts文件

windows系统的hosts文件的位置如下：
C:\Windows\System32\drivers\etc\hosts
mac/linux系统的hosts文件的位置如下：drivers/etc/hosts
win+r输入： drivers/etc/hosts
2. 打开本地hosts文件：
    增加http://github.global.ssl.fastly.net和http://github.com的映射

获取Github相关网站的ip
访问https://www.ipaddress.com  ，拉下来，
找到页面中下方的“IP Address Tools – Quick Links”
（直接在能看到的搜索框搜索即可）
分别输入github.global.ssl.fastly.net    和   github.com  ，
查询ip地址
将以下配置复制到你的hosts中保存 

上一步查到的ip	github.com
上一步查到的ip	github.global.ssl.fastly.net

例如
127.0.0.1	github.com
127.0.0.1	github.global.ssl.fastly.net





























