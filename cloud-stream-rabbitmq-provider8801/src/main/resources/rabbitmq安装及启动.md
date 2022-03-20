ps : 本文档内容均来自官网，推荐直接查看官网

基于rabbitmq官方文档：安装在虚拟机上centos7-linux上
安装日期 2022年2月28日

[rabbitmq官方安装指导手册](https://www.rabbitmq.com/install-rpm.html)

配合谷歌翻译查看   aaaaa

本机笔记本使用以下安装方式：
[使用 PackageCloud Yum 存储库安装](https://www.rabbitmq.com/install-rpm.html#package-cloud)
0 执行

    curl -s https://packagecloud.io/install/repositories/rabbitmq/rabbitmq-server/script.rpm.sh | sudo bash

1 执行：

    ## 使用 PackageCloud 提供的 Yum 存储库设置脚本。
    ## 在将下载的内容通过管道传输到特权 shell 之前，请始终验证下载的内容！
    yum update -y
    yum -q makecache -y --disablerepo='*' --enablerepo='rabbitmq_erlang' --enablerepo='rabbitmq_server'

2 执行：

    ## 主要 RabbitMQ 签名密钥
    ## 现代 Erlang 存储库
    rpm --import https://github.com/rabbitmq/signing-keys/releases/download/2.0/rabbitmq-release-signing-key.asc 
    ## RabbitMQ 服务器存储库
    rpm --import https: //packagecloud.io/rabbitmq/erlang/gpgkey 
    rpm --import https://packagecloud.io/rabbitmq/rabbitmq-server/gpgkey

3 执行：

    vim /etc/yum.repos.d/rabbitmq.repo # 为 RabbitMQ 和 Modern Erlang 添加 Yum 存储库

复制以下文本并wq保存

# 在 /etc/yum.repos.d/rabbitmq.repo

## 
## 零依赖 Erlang
## 

[rabbitmq_erlang]
name =rabbitmq_erlang
baseurl =https://packagecloud.io/rabbitmq/erlang/el/ 8 / $basearch
repo_gpgcheck = 1
gpgcheck = 1
enabled = 1
# PackageCloud 的存储库密钥和 RabbitMQ 包签名密钥
gpgkey =https://packagecloud.io/rabbitmq/erlang/gpgkey
https://github.com/rabbitmq/signing-keys/releases/download/ 2.0/rabbitmq-release-signing-key.asc sslverify = 1 sslcacert

=/etc/pki/tls/certs/ca-bundle.crt
metadata_expire = 300

[rabbitmq_erlang-source]
名称=rabbitmq_erlang-source
baseurl =https://packagecloud.io/rabbitmq/erlang/el/ 8 /SRPMS
repo_gpgcheck = 1
gpgcheck = 0
enabled = 1
# PackageCloud 的存储库密钥和 RabbitMQ 包签名密钥
gpgkey =https://packagecloud.io/rabbitmq/erlang/gpgkey
https://github.com/rabbitmq/signing-keys/releases/download/2.0/ rabbitmq-release-signing-key.asc sslverify = 1 sslcacert =/etc/pki/tls/certs/ca-bundle.crt


metadata_expire = 300

## 
## RabbitMQ 服务器
## 

[rabbitmq_server]
name =rabbitmq_server
baseurl =https://packagecloud.io/rabbitmq/rabbitmq-server/el/ 8 / $basearch
repo_gpgcheck = 1
gpgcheck = 0
enabled = 1
# PackageCloud's存储库密钥和 RabbitMQ 包签名密钥
gpgkey =
https://packagecloud.io/rabbitmq/rabbitmq-server/gpgkey https://github.com/rabbitmq/signing-keys/releases/download/2.0/rabbitmq-release-signing- key.asc sslverify = 1 sslcacert

=/etc/pki/tls/certs/ca-bundle.crt
metadata_expire = 300

[rabbitmq_server-source]
名称=rabbitmq_server-source
baseurl =https://packagecloud.io/rabbitmq/rabbitmq-server/el/ 8 /SRPMS
repo_gpgcheck = 1
gpgcheck = 0
启用= 1
gpgkey =https://packagecloud.io/rabbitmq/rabbitmq-server/gpgkey
sslverify = 1
sslcacert =/etc/pki/tls/certs/ca-bundle.crt
metadata_expire = 300

4 执行：

    # 更新 Yum 包元数据：
    yum update -y

    yum -q makecache -y --disablerepo='*' --enablerepo='rabbitmq_erlang' --enablerepo='rabbitmq_server'

5 执行：

    ## 从标准操作系统存储库安装这些依赖 install these dependencies from standard OS repositories
    yum install socat logrotate -y

6 执行：

    ## 从上述存储库安装 RabbitMQ 和零依赖 Erlang，
    ## 忽略标准存储库提供的任何版本
    yum install --repo rabbitmq_erlang --repo rabbitmq_server-noarch erlang rabbitmq-server

7 执行：
    
    ## 从上述存储库安装 RabbitMQ 和零依赖 Erlang，
    ## 忽略标准存储库提供的任何版本
    yum install rabbitmq_erlang  rabbitmq_server-noarch erlang rabbitmq-server

安装结束

#### 位置：
    whereis rabbitmq
#### 开放端口5672/15672

#### 运行 RabbitMQ 服务器
启动服务器
安装 RabbitMQ 服务器包时，服务器默认不作为守护进程启动。在系统启动时默认启动守护进程，
以管理员身份运行

    chkconfig rabbitmq-server on

作为管理员，像往常一样启动和停止服务器，例如使用service：

    /sbin/service rabbitmq-server start
    
    /sbin/service rabbitmq-server status
    
    /sbin/service rabbitmq-server stop

或：
/bin/systemctl start rabbitmq-server.service
rabbitmq-server -detached  # detached 分离的 表示

rabbitmq-diagnostics status

#### 登录网址 ip:15672
[本机笔记本](http://192.168.124.21:15672)
[本机台式机暂时](http://192.168.220.128:15672)
账号admin 密码admin
#### 查看版本：

    sudo rabbitmq-diagnostics cluster_status

journalctl --system

#### 具体配置待学习

http://192.168.220.128:15672/#/
