#如何安装nginx
[官网](https://nginx.org/) 获取 stable稳定的

weget https://nginx.org/download/nginx-1.20.2.tar.gz
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
 nginx/conf/nginx.conf
    [root@localhost conf]# cd /usr/local/nginx/conf
