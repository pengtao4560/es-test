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

    在linux下安装nginx，首先需要安装 gcc-c++编译器。然后安装nginx依赖的pcre和zlib包。最后安装nginx即可。
    
    1.先安装gcc-c++编译器
    
    yum install gcc-c++
    yum install -y openssl openssl-devel
    2.再安装pcre包
    
    yum install -y pcre pcre-devel
    3.再安装zlib包
    
    yum install -y zlib zlib-devel

1.在/usr/local/下创建文件nginx文件

mkdir /usr/local/nginx
2.下载：官网复制路径 wget 路径

wget https://nginx.org/download/nginx-1.20.2.tar.gz
3.解压

4. 进入解压后的目录使用nginx默认配置，执行
5. 
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

### nginx 配置实例-负载均衡
### nginx 原理与优化参数配置
### nginx 搭建高可用集群
### nginx集群：Keepalived+Nginx 高可用集群（主从模式）
### nginx集群：Keepalived+Nginx 高可用集群（双主模式）
