#!/bin/sh 
DUMP=/usr/bin/mysqldump     #mysqldump的路径,不知道的可以全局搜索一下  find / -name mysqldump 
OUT_DIR=/home/mysqldumpbak20211209        #备份文件的目录，没有提前建好
DB_NAME=database        #要备份的数据库名字
DB_USER=root          #数据库登录名
DB_PASS=root           #数据库登陆密码
HOST=127.0.0.1    #远程备份ip
USE=root               #远程备份用户
DATE=`date +%Y%m%d%H%M`        #当前时间
OUT_SQL="mysqldata_bak_$DATE.sql"     #备份出来的sql文件名
$DUMP  -u$DB_USER -p$DB_PASS $DB_NAME |  -c | ssh $USE@$HOST "cat >$OUT_DIR/$OUT_SQL"