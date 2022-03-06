1. yml文件的格式注意。否则报错
Failed to bind properties under 'eureka.client.service-url' 
to java.util.Map<java.lang.String, java.lang.String>:

第二行路径换成本地git安装的路径  G:\1softwareInstall1\git\Git\mingw64\ssl 拼接上 \cert.pem
git com.atguigu.springcloud.com.atguigu.config --global http.sslBackend "openssl"
git com.atguigu.springcloud.com.atguigu.config --global http.sslCAInfo "G:\1softwareInstall1\git\Git\mingw64\ssl\cert.pem"
git com.atguigu.springcloud.com.atguigu.config --global http.sslCAInfo "G:\1softwareInstall1\git\Git\mingw64\ssl\cert.pem"
git com.atguigu.springcloud.com.atguigu.config --global http.sslVerify "false"

git com.atguigu.springcloud.com.atguigu.config --global http.sslVerify "false"

centos6 不支持yum 一键复制解决：
[参考博客](https://www.xmpan.com/944.html)
sed -i "s|enabled=1|enabled=0|g" /etc/yum/pluginconf.d/fastestmirror.conf
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
curl -o /etc/yum.repos.d/CentOS-Base.repo https://www.xmpan.com/Centos-6-Vault-Aliyun.repo
yum clean all
yum makecache
