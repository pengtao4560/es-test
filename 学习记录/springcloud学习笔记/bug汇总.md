1. yml文件的格式注意。否则报错
Failed to bind properties under 'eureka.client.service-url' 
to java.util.Map<java.lang.String, java.lang.String>:

第二行路径换成本地git安装的路径  G:\1softwareInstall1\git\Git\mingw64\ssl 拼接上 \cert.pem
git com.atguigu.springcloud.config --global http.sslBackend "openssl"
git com.atguigu.springcloud.config --global http.sslCAInfo "G:\1softwareInstall1\git\Git\mingw64\ssl\cert.pem"
git com.atguigu.springcloud.config --global http.sslCAInfo "G:\1softwareInstall1\git\Git\mingw64\ssl\cert.pem"
git com.atguigu.springcloud.config --global http.sslVerify "false"

git com.atguigu.springcloud.config --global http.sslVerify "false"
