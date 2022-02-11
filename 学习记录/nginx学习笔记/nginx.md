###nginx概述
    Nginx ("engine x") 是一个高性能的 HTTP 和反向代理服务器,特点是占有内存少，并发能
    力强，事实上 nginx 的并发能力确实在同类型的网页服务器中表现较好，中国大陆使用 nginx
    网站用户有：百度、京东、新浪、网易、腾讯、淘宝等
### nginx作为web服务器
    Nginx 可以作为静态页面的 web 服务器，同时还支持 CGI 协议的动态语言，比如 perl、php
    等。但是不支持 java。Java 程序只能通过与 tomcat 配合完成。Nginx 专为性能优化而开发，
    性能是其最重要的考量,实现上非常注重效率 ，能经受高负载的考验,有报告表明能支持高
    达 50,000 个并发连接数。
### nginx正向代理、反向代理、负载均衡
    正向代理：如果把局域网外的 Internet 想象成一个巨大的资源库，则局域网中的客户端要访
    问 Internet，则需要通过代理服务器来访问，这种代理服务就称为正向代理

    【百度百科】意思是一个位于客户端和原始服务器(origin server)之间的服务器，
    为了从原始服务器取得内容，客户端向代理发送一个请求并指定目标(原始服务器)，
    然后代理向原始服务器转交请求并将获得的内容返回给客户端。客户端才能使用正向代理。

    反向代理: 其实客户端对代理是无感知的，因为客户端不需要任何配置就可以访问，我们只
    需要将请求发送到反向代理服务器，由反向代理服务器去选择目标服务器获取数据后，在返
    回给客户端，此时反向代理服务器和目标服务器对外就是一个服务器，暴露的是代理服务器
    地址，隐藏了真实服务器 IP 地址。

    负载均衡：负载均衡，英文名称为Load Balance，其含义就是指将负载（工作任务）进行平衡、分摊到多个操作单元上进行运行，
    例如FTP服务器、Web服务器、企业核心应用服务器和其它主要任务服务器等，从而协同完成工作任务。
    (增加服务器的数量，然后将请求分发到各个服务器上，将原先请求集中到单个服务器上的情况改为将请求分发到多个服务器上，
    将负载分发到不同的服务器，也就是我们所说的负载均衡)

    动静分离: 为了加快网站的解析速度，可以把动态页面和静态页面由不同的服务器来解析，加快解析速
    度。降低原来单个服务器的压力。

[Nginx介绍及Nginx的优点](https://lnmp.org/nginx.html)

#如何安装nginx
[官网](https://nginx.org/) 获取 stable稳定的
 前提：

    新的虚拟机 可以 yum update一下
    yum update
    在linux下安装nginx，首先需要安装 gcc-c++编译器。然后安装nginx依赖的pcre和zlib包。最后安装nginx即可。
    
    1.先安装gcc-c++编译器
    
    yum install gcc-c++
    yum install -y openssl openssl-devel
    2.再安装pcre包
    
    yum install -y pcre pcre-devel
    3.再安装zlib包
    
    yum install -y zlib zlib-devel

安装步骤

    1.在/usr/local/下创建文件nginx文件(第一步好像不需要)

    mkdir /usr/local/nginx
    2.下载：官网复制路径 wget 路径

    wget https://nginx.org/download/nginx-1.20.2.tar.gz
    3.解压 tar -xzvf ng ...

    4.进入解压后的目录使用nginx默认配置，执行
    ./configure
    5.编译安装：

    make && make install
    6.查找安装路径

    whereis nginx
    7.进入sbin目录，可以看到有一个可执行文件nginx，直接./nginx执行就OK了。

    ./nginx

或百度或参考[尚硅谷nginx](nginx课件v1.0.pdf)

### nginx查看版本
whereis nginx

cd /usr/local/nginx/sbin

./nginx -v

./nginx -V

    [root@localhost sbin]# ./nginx -v
    nginx version: nginx/1.18.0
    [root@localhost sbin]# ./nginx -V
    nginx version: nginx/1.18.0
    built by gcc 4.4.7 20120313 (Red Hat 4.4.7-16) (GCC)
    configure arguments: --prefix=/usr/local/nginx
    [root@localhost sbin]# pwd
    /usr/local/nginx/sbin
    [root@localhost sbin]#
### nginx启动、停止、重新加载
./nginx

./nginx -s stop

./nginx -s reload

    [root@localhost sbin]# ./nginx 
    [root@localhost sbin]# ./nginx -s reload
    [root@localhost sbin]# ./nginx -s stop
    [root@localhost sbin]# ./nginx -s reload
    nginx: [error] open() "/usr/local/nginx/logs/nginx.pid" failed (2: No such file or directory)
    [root@localhost sbin]# ./nginx
    [root@localhost sbin]# .

### nginx配置文件
[nginx配置文件](nginx.conf)
 nginx/conf/nginx.conf
    [root@localhost conf]# cd /usr/local/nginx/conf
 根据上述文件，我们可以很明显的将 nginx.conf 配置文件分为三部分：

     第一部分：全局块
         从配置文件开始到 events 块之间的内容，主要会设置一些影响 nginx 服务器整体运行的配置指令，主要包括配
         置运行 Nginx 服务器的用户（组）、允许生成的 worker process 数，进程 PID 存放路径、日志存放路径和类型以
         及配置文件的引入等。
         比如上面第一行配置的：
         这是 Nginx 服务器并发处理服务的关键配置，worker_processes 值越大，可以支持的并发处理量也越多，但是
         会受到硬件、软件等设备的制约
     第二部分：events 块
         events 块涉及的指令主要影响 Nginx 服务器与用户的网络连接，常用的设置包括是否开启对多 work process
         下的网络连接进行序列化，是否允许同时接收多个网络连接，选取哪种事件驱动模型来处理连接请求，每个 word
         process 可以同时支持的最大连接数等。
         上述例子就表示每个 work process 支持的最大连接数为 1024.
         这部分的配置对 Nginx 的性能影响较大，在实际中应该灵活配置。
     第三部分：http 块
         这算是 Nginx 服务器配置中最频繁的部分，代理、缓存和日志定义等绝大多数功能和第三方模块的配置都在这里。
         需要注意的是：http 块也可以包括 http 全局块、server 块。
         ①、http 全局块
         http 全局块配置的指令包括文件引入、MIME-TYPE 定义、日志自定义、连接超时时间、单链接请求数上限等。
         ②、server 块
         这块和虚拟主机有密切关系，虚拟主机从用户角度看，和一台独立的硬件主机是完全一样的，该技术的产生是为了
         节省互联网服务器硬件成本。
         每个 http 块可以包括多个 server 块，而每个 server 块就相当于一个虚拟主机。
         而每个 server 块也分为全局 server 块，以及可以同时包含多个 locaton 块。
         1、全局 server 块
         最常见的配置是本虚拟机主机的监听配置和本虚拟主机的名称或 IP 配置。
         2、location 块
         一个 server 块可以配置多个 location 块。
         这块的主要作用是基于 Nginx 服务器接收到的请求字符串（例如 server_name/uri-string），对虚拟主机名称
         （也可以是 IP 别名）之外的字符串（例如 前面的 /uri-string）进行匹配，对特定的请求进行处理。地址定向、数据缓
         存和应答控制等功能，还有许多第三方模块的配置也在这里进行。
### nginx 配置实例-反向代理
准备工作 linux安装tomcat

wget --no-check-certificate https://tomcat.apache.org/download-10.cgi#10.0.16
[tomcat安装](https://www.linuxprobe.com/linux-install-tomcat9.html)
进入tomcat下的bin目录
cd /usr/local/apache-tomcat-10.0.16/bin
./catalina.sh start
[root@izuf6famz0x92jd98na1kiz bin]# ./catalina.sh start
对外开放访问的端口：
yy复制 p粘贴
proxy_pass http://127.0.0.1:8080;
###centos6开放端口
vim /etc/sysconfig/iptables
yy复制 
nyy复制光标所在行当前行及向下n行 
dd删行 
p粘贴

重启
service iptables restart

###centos7开放端口
firewall cmd add port=80 80 /tcp permanent
firewall cmd reload
查看已经开放的端口号
firewall cmd list all

win+r： /drivers/etc/hosts
追加：
#尚硅谷nginx学习-反向代理实例一
192.168.159.131:8080 www.123.com

linux nginx.conf修改：

    server {
    listen       80;
    server_name  192.168.159.131;
    
            #charset koi8-r;
    
            #access_log  logs/host.access.log  main;
    
            location / {
                root   html;
                proxy_pass http://127.0.0.1:8080;
                index  index.html index.htm;
            }
./nginx -s reload
#nginx反向代理-实例2
实现效果：

    使用 nginx 反向代理， 根据访问的路径跳转到不同端口的服务中
    nginx 监听端口为 9001
    访问 http://127.0.0.1:9001/edu/ 直接跳转到 127.0.0.1:808 1
    访问 http://1 27.0.0.1:9001/vod/ 直接跳转到 127.0.0.1:808 2

(1)准备两个tomcat
server.xml配置文件 tomcat8080不用改，直接启动
tomcat8081改动如下：
？8080        8080 -> 8081
？8009        8009 -> 8019
？8005        8005 -> 8015
启动
pe -ef|grep tomcat
防火墙加上开放端口8081
(2)创建文件夹和测试页面

    [root@pengtao webapps]# pwd
    /usr/local/tomcat8080/apache-tomcat-10.0.16/webapps
    [root@pengtao webapps]# mkdir edu
    [root@pengtao webapps]# touch a.html
    [root@pengtao webapps]# vim a.html
    <a>8080!!</a>

    [root@pengtao webapps]# pwd
    /usr/local/tomcat8081/apache-tomcat-10.0.16/webapps
    [root@pengtao webapps]# mkdir edu
    [root@pengtao webapps]# touch a.html
    [root@pengtao webapps]# vim a.html
    <a>8081!!</a>
台式机虚拟机ip
访问[](http://192.168.159.131:8080/edu/a.html)
访问[](http://192.168.159.131:8081/edu/a.html)
访问[](http://192.168.159.131:8081/vod/a.html)
笔记本虚拟机ip:
访问[](http://192.168.124.20:8080/edu/a.html)
访问[](http://192.168.124.20:8081/edu/a.html)
访问[](http://192.168.124.20:8081/vod/a.html)

(3)nginx配置文件修改，进行反向代理配置
增加

server {
listen       9001;
server_name  192.168.159.131;

location ~ /edu/ {
proxy_pass http://127.0.0.1:8080;
}
location ~ /vod/ {
proxy_pass http://127.0.0.1:8081;
}
}

# location指令：
location 指令说明
该指令用于匹配 URL。 语法如下：
1、= ：用于不含正则表达式的 uri 前，要求请求字符串与 uri 严格匹配，如果匹配成功，
就停止继续向下搜索并立即处理该请求。
2、~：用于表示 uri 包含正则表达式，并且区分大小写。
3、~*：用于表示 uri 包含正则表达式，并且不区分大小写。
4、^~：用于不含正则表达式的 uri 前，要求 Nginx 服务器找到标识 uri 和请求字符串匹配度最高的 location 后，
立即使用此 location 处理请求，而不再使用 location 块中的正则 uri 和请求字符串做匹配。
注意：如果 uri 包含正则表达式，则必须要有 ~ 或者 ~* 标识。

(4) 开放9001端口
(5)测试
台式机虚拟机ip
访问[](http://192.168.159.131:9001/edu/a.html)
访问[](http://192.168.159.131:9001/vod/a.html)

笔记本虚拟机ip:
访问[](http://192.168.124.20:9001/edu/a.html)
访问[](http://192.168.124.20:9001/vod/a.html)
### 问题：nginx第10节课 tomcat8081不创建vod文件夹能访问到么

### nginx 配置实例-负载均衡
实现效果：浏览器地址栏输入地址
l，负载均衡效果，平均分发到8080和8081端口中
[](http://192.168.159.131/edu/a.html)

准备工作：
1.准备两台tomcat服务器 8080和8081
2.在两台tomcat里面webapps目录中，创建edu文件夹，创建页面a.html，用于测试

    [root@pengtao edu]# pwd
    /usr/local/tomcat8080/apache-tomcat-10.0.16/webapps/edu
    [root@pengtao edu]# ls
    a.html
    [root@pengtao edu]# cd /usr/local/tomcat8081/apache-tomcat-10.0.16/webapps/edu
    [root@pengtao edu]# ls
    a.html
    [root@pengtao edu]#
测试：
访问[](http://192.168.159.131:8080/edu/a.html)
访问[](http://192.168.159.131:8081/edu/a.html)
3.修改nginx配置文件：增加 upstream 服务名1  server里 http://服务名1

     upstream myserver {
        server 192.168.159.131:8080;
        server 192.168.159.131:8081;
        
    }
    server {
        listen       80;
        server_name  192.168.159.131;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root html;
            proxy_pass http://myserver;
            index  index.html index.htm;
        }
修改后的配置文件备份：[](nginx-负载均衡.conf)
4.重新加载
./nginx -s reload

    [root@pengtao conf]# vim nginx.conf
    [root@pengtao conf]# ./../sbin/nginx -s reload
    [root@pengtao conf]#
5.台式机测试，访问 [](http://192.168.159.131/edu/a.html) 并多次刷新
  笔记本测试，访问 [](http://192.168.124.20/edu/a.html) 并多次刷新

###nginx策略：
    第一种 轮询（默认）
    每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器 down 掉，能自动剔除。
    第二种 weight
    weigh t 代表权重默认为 1, 权重越高被分配的客户端越多
    配置：
    upstream myserver {
        server 192.168.159.131:8080 weight=10;
        server 192.168.159.131:8081 weight=3;
        
    }
    第三种 ip_hash
    每个请求按访问 ip 的 hash 结果分配，这样每个访客固定访问一个后端服务器
    配置：
    upstream myserver {
        ip_hash
        server 192.168.159.131:8080;
        server 192.168.159.131:8081;
    }
    第四种 fair （第三方）
    按后端服务器的响应时间来分配请求，响应时间短的优先分配。
    配置：
    upstream myserver {
        server 192.168.159.131:8080;
        server 192.168.159.131:8081;
        fair
    }
### nginx 配置实例-动静分离
    Nginx 动静分离简单来说就是把动态跟静态请求分开，不能理解成只是单纯的把动态页面和静态页面物理分离。
    严格意义上说应该是动态请求跟静态请求分开，可以理解成使用Nginx 处理静态页面，Tomcat处理动态页面。
    动静分离从目前实现角度来讲大致分为两种， 一种是纯粹把静态文件独立成单独的域名，放在独立的服务器上，
    也是目前主流推崇的方案； 另外一种方法就是动态跟静态文件混合在一起发布，通过 nginx 来分开。

    通过 location 指定不同的后缀名实现不同的请求转发。通过 expires 参数设置，可以使浏览器缓存过期时间，
    减少与服务器之前的请求和流量。具体 Expires 定义：是给一个资源设定一个过期时间，也就是说无需去服务端验证，
    直接通过浏览器自身确认是否过期即可，所以不会产生额外的流量。此种方法非常适合不经常变动的资源。
    （如果经常更新的文件，不建议使用 Expires 来缓存），我这里设置 3d，表示在这 3 天之内访问这个 URL，
    发送一个请求，比对服务器该文件最后更新时间没有变化，则不会从服务器抓取，返回状态码 304，如果有修改
    ，则直接从服务器重新下载，返回状态码 200。 
准备工作：
 （1）在linux系统中准备静态资源，用于进行访问

    [root@pengtao conf]# mkdir -p /data/www /data/image
    [root@pengtao conf]# cd /data
    [root@pengtao data]# ls
    image  www
    [root@pengtao data]#
[笔记本测试](http://192.168.124.20/image/月份缩写英文.jpg)
[笔记本测试](http://192.168.124.20/www/a.html)

[台式机测试](http://192.168.159.131/image/月份缩写英文.jpg)
[台式机测试](http://192.168.159.131/www/a.html)

[](http://192.168.159.131/image/nginx-动静分离.png)

[](http://192.168.159.131/www/a.html)
 


### nginx 原理与优化参数配置
### nginx 搭建高可用集群
### nginx集群：Keepalived+Nginx 高可用集群（主从模式）
yum install keepalived -y
rpm -qa|grep keepalived

[root@192 local]# rpm -qa|grep keepalived
keepalived-1.3.5-19.el7.x86_64

    keepalived-1.3.5-19.el7.x86_64
    [root@192 local]# whereis keepalived
    keepalived: /usr/sbin/keepalived /etc/keepalived /usr/libexec/keepalived /usr/share/man/man8/keepalived.8.gz
    [root@192 local]# cd /etc/keepalived/
    [root@192 keepalived]# ls
    keepalived.conf
1. nginx高可用：
2. 配置高可用的准备工作
1 ）需要两台服务器 192.168.17.129 和 192.168.17.131
2 ）在两台服务器安装 nginx
3 ）在两台服务器安装 keepalived
3. 在两台服务器安装 keepalived
1 ） 使用 yum 命令进行安装
2 ）安装之后，在 etc 里面生成目录 keepalived ，有文件 keepalived.conf
4 、完成高可用配置（主从配置）
[ keepalived主机配置文件参考：](keepalived.conf)
[ keepalived备机配置文件参考：](keepalived-backup.conf)

[keepalived的脚本文件](nginx_check.sh)


5 测试效果一：[nginx 主备绑定虚拟ip](http://192.168.159.50)
主服务器命令
ip a

    root@pengtao sbin]# ip a 
    1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
    inet6 ::1/128 scope host
    valid_lft forever preferred_lft forever
    2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP qlen 1000
    link/ether 00:0c:29:c5:87:38 brd ff:ff:ff:ff:ff:ff
    inet 192.168.159.131/24 brd 192.168.159.255 scope global eth0
    inet 192.168.159.50/32 scope global eth0
    inet6 fe80::20c:29ff:fec5:8738/64 scope link
    valid_lft forever preferred_lft forever
    [root@pengtao sbin]# 

inet 192.168.159.50/32 scope global eth0 这句话表示主服务器已经绑定了一个虚拟ip

测试效果二：
停掉主服务器nginx，依旧可以访问  [nginx 主备绑定虚拟ip](http://192.168.159.50)

vim /etc/hosts
echo "127.0.0.0 LVS_DEVEL" >> /etc/hosts
### nginx集群：Keepalived+Nginx 高可用集群（双主模式）


### nginx 原理
# 一个master和多个worker的好处
 1.可以使用nginx -s reload 热部署
 2. 每个worker是独立的进程，如果有其中的一个worker出现问题，其他worker独立的继续进行争抢，
   实现请求过程，不会造成服务中断
# 设置多少个worker 合适
 Nginx 和redis 类似 都采用 io多路复用机制，每个worker都是一个独立的进程，但配个进程里面只有一个主进
 
 worker数量和服务器的cpu数相等是最为适宜的


## 连接数 worker_connection
问题1：发送请求，占用了几个连接数。
答案：要么是2个，要么是4个 看实际场景

问题2：nginx有一个master，有4个worker，每个worker支持的最大连接数据1024
支持的最大并发数是多少？

答案：4 * 1024 的结果 除以2 或者 除以 4
公式为：

普通的静态访问最大并发数是： worker_connections * work_processes /2
而如果是nginx  作为HTTP反向代理（服务器）来说，最大并发数量是  worker_connections * work_processes / 4
