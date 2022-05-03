## 10.RabbitMQ 集群


### 10.1.clustering

### 10.1.1.使用集群的原因

最开始我们介绍了如何安装及运行 RabbitMQ 服务，不过这些是单机版的，无法满足目前真实应用的要求。如果 RabbitMQ 服务器遇到内存崩溃、机器掉电或者主板故障等情况，
该怎么办？单台 RabbitMQ 服务器可以满足每秒 1000 条消息的吞吐量，那么如果应用需要 RabbitMQ 服务满足每秒 10 万条消息的吞

吐量呢？购买昂贵的服务器来增强单机 RabbitMQ 务的性能显得捉襟见肘，搭建一个 RabbitMQ 集群才是解决实际问题的关键.

### 10.1.2.搭建步骤
##### 步骤1：
vmvare中 关闭虚拟机。选中当前虚拟机 右键 管理->克隆->克隆当前->克隆完整->选择路径-> 等待克隆完成->启动->ifconfig查看ip

pengtao4560 台式机

    ip: 192.168.220.128
    ip: 192.168.220.129
    ip: 192.168.220.130

修改 3 台机器的主机名称
vim /etc/hostname
node1
node2
node3

##### 步骤2：
配置各个节点的 hosts 文件，让各个节点都能互相识别对方

    192.168.220.128 node1
    192.168.220.129 node2
    192.168.220.130 node3

##### 步骤3：

以确保各个节点的 cookie 文件使用的是同一个值在 node1 上执行远程操作命令
在node1机器上粘贴以下指令执行：

    scp /var/lib/rabbitmq/.erlang.cookie root@node2:/var/lib/rabbitmq/.erlang.cookie 
    scp /var/lib/rabbitmq/.erlang.cookie root@node3:/var/lib/rabbitmq/.erlang.cookie

##### 步骤4：
启动 RabbitMQ 服务,顺带启动 Erlang 虚拟机和 RbbitMQ 应用服务(在三台节点上分别执行以下命令)
rabbitmq-server -detached

##### 步骤5.在节点 2 执行

    #(rabbitmqctl stop 会将Erlang 虚拟机关闭，rabbitmqctl stop_app 只关闭 RabbitMQ 服务)
    rabbitmqctl stop_app    
    rabbitmqctl reset
    rabbitmqctl join_cluster rabbit@node1
    #(只启动应用服务)
    rabbitmqctl start_app 

#####6.在节点 3 执行

rabbitmqctl stop_app 
rabbitmqctl reset
rabbitmqctl join_cluster rabbit@node2 
rabbitmqctl start_app

##### 6.5 3台节点的防火墙需要开启两个端口：
    firewall-cmd --permanent --add-port=4369/tcp
    firewall-cmd --permanent --add-port=25672/tcp
    firewall-cmd --reload

#####7.集群状态
rabbitmqctl cluster_status 
#####8.需要重新设置用户
创建账号
rabbitmqctl add_user admin admin
设置用户角色
rabbitmqctl set_user_tags admin administrator
设置用户权限
rabbitmqctl set_permissions -p "/" admin ".*" ".*" ".*"
测试
[](http://192.168.220.128:15672/#/)
[](http://192.168.220.129:15672/#/)
[](http://192.168.220.130:15672/#/)
##### 9(可以不执行).解除集群节点(node2 和 node3 机器分别执行) 
rabbitmqctl stop_app
rabbitmqctl reset 
rabbitmqctl start_app 
rabbitmqctl cluster_status
rabbitmqctl forget_cluster_node rabbit@rabbitmq   (node1 机器上执行)


报错日志：
15:58:46.015 [warn]  Feature flags: the previous instance of this node must have failed to write the `feature_flags` file at `/var/lib/rabbitmq/mnesia/rabbit@rabbitmq-feature_flags`:

15:58:46.028 [warn]  Feature flags:   - list of previously disabled feature flags now marked as such: [:maintenance_mode_status]

15:58:46.562 [error] Failed to create a tracked connection table for node :rabbit@rabbitmq: {:node_not_running, :rabbit@rabbitmq}

15:58:46.562 [error] Failed to create a per-vhost tracked connection table for node :rabbit@rabbitmq: {:node_not_running, :rabbit@rabbitmq}

15:58:46.562 [error] Failed to create a per-user tracked connection table for node :rabbit@rabbitmq: {:node_not_running, :rabbit@rabbitmq}
[root@rabbitmq ~]# 

解决： 因为一号节点 node1 的rabbitmq 没有启动 启动即可
