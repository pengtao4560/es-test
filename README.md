学习 哔哩哔哩尚硅谷 springcloud课程、java8新特性、nginx、linux

未来：redis、mongo、消息队列、mysql进阶、
##### markdown文件表情

语法： &#xCODE;    
其中，CODE 可以从 [Emoji Unicode Tables](https://apps.timwhitlock.info/emoji/tables/unicode#block-4-enclosed-characters)
中查到。复制第四排的： Symbola [4] 即可

    例子： 查到了 表情对应的 Unicode 编码为 U+1F34E，则与此表情对应的 CODE 为 1F34E (舍弃前面的 U+)。
    我们只需在 Markdown 文档中输入 &#x1F34E; 即可显示为 。
##### markdown文件表情复制
😄
🙋
✅
✏
❓
💪
🐼
👊
💖
##### [git把某个文件去除版本控制不删除本地文件](https://my.oschina.net/yurenzhen/blog/1800790)

    git rm -h 查看说明
    git rm --cached 文件的全路径名
    -r 是允许递归删除，当要删除的是文件夹的时候有用
    git rm -r --cached loggers  # -r 是允许递归删除，当要删除的是文件夹的时候有用

##### [AnotherRedisDesktopManager redis可视化管理工具 github下载](https://github.com/qishibo/AnotherRedisDesktopManager/releases)
##### [AnotherRedisDesktopManager redis可视化管理工具 gitee下载](Gitee：https://gitee.com/qishibo/AnotherRedisDesktopManager/releases)
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


##名词解释：
SDK （Software Development Kit）
👍：
    软件开发工具包一般都是一些软件工程师为特定的软件包、软件框架、硬件平台、操作系统等建立应用软件时的开发工具的集合。
    软件开发工具广义上指辅助开发某一类软件的相关文档、范例和工具的集合。
    软件开发工具包是一些被软件工程师用于为特定的软件包、软件框架、硬件平台、操作系统等创建应用软件的开发工具的集合，一般而言SDK即开发Windows平台下的应用程序所使用的SDK。它可以简单的为某个程序设计语言提供应用程序接口API的一些文件，但也可能包括能与某种嵌入式系统通讯的复杂的硬件。一般的工具包括用于调试和其他用途的实用工具。SDK还经常包括示例代码、支持性的技术注解或者其他的为基本参考资料澄清疑点的支持文档。
    为了鼓励开发者使用其系统或者语言，许多SDK是免费提供的。软件工程师通常从目标系统开发者那里获得软件开发包，也可以直接从互联网下载，有时也被作为营销手段。例如，营销公司会免费提供构建SDK以鼓励人们使用它，从而会吸引更多人由于能免费为其编程而购买其构件。
    SDK可能附带了使其不能在不兼容的许可证下开发软件的许可证。例如产品
[SDK百度百科](https://baike.baidu.com/item/%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91%E5%B7%A5%E5%85%B7%E5%8C%85/10418833?fromtitle=SDK&fromid=7815680&fr=aladdin)

API （Application Programming Interface，应用编程接口）

    其实就是操作系统留给应用程序的一个调用接口，
    应用程序通过调用操作系统的API而使操作系统去执行应用程序的命令（动作）。
    其实早在DOS时代就有API的概念，只不过那个时候的API是以中断调用的形式（INT 21h）提供的，
    在DOS下跑的应用程序都直接或间接的通过中断调用来使用操作系统功能，
    比如将AH置为30h后调用INT 21h就可以得到DOS 操作系统的版本号。
    而在Windows中，系统API是以函数调用的方式提供的。同样是取得操作系统的版本号，
    在Windows中你所要做的就是调用GetVersionEx()函数。




[redis官网](https://redis.io)
[redis下载](https://redis.io/download)
[redis 安装教程](https://www.cnblogs.com/hunanzp/p/12304622.html)
wget https://download.redis.io/releases/redis-6.2.6.tar.gz
解压
改名字 mv redis-6.2.6 redis
cd redis
pwd            /usr/local/redis

make
cd src
make install
修改配置文件 redis.conf
cp /usr/local/redis/redis.conf redis.conf.bak
vim 


##### redis 单机启动
find / -name redis
find / -name redis.conf

cd /usr/local/redis
./bin/redis-server /usr/local/redis/redis.conf

[配置文件参数说明](https://lion-wu.blog.csdn.net/article/details/108019877?spm=1001.2101.3001.6650.13&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-13.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-13.pc_relevant_default&utm_relevant_index=16)















####知识扩展
##### BI: 商务智能 (business intelligence)
smartbi[浅谈Smartbi和Power BI](https://zhuanlan.zhihu.com/p/165376092)

[持续集成 CI Continuous integration](https://baike.baidu.com/item/%E6%8C%81%E7%BB%AD%E9%9B%86%E6%88%90/6250744?fr=aladdin)
[持续交付 CD Continuous Delivery](https://baike.baidu.com/item/%E6%8C%81%E7%BB%AD%E4%BA%A4%E4%BB%98/9803571)
[CI/CD](https://www.redhat.com/zh/topics/devops/what-is-ci-cd)
[CI/CD](https://zhuanlan.zhihu.com/p/228272483)



在谈论软件开发时，经常会提到 持续集成(Continuous Integration)（CI）和 持续交付(Continuous Delivery)（CD）这几个术语。
但它们真正的意思是什么呢？在本文中，我将解释这些和相关术语背后的含义和意义，例如 
持续测试(Continuous Testing)和 持续部署(Continuous Deployment)。

##### CI 持续集成（Continuous Integration）
现代应用开发的目标是让多位开发人员同时处理同一应用的不同功能。但是，如果企业安排在一天内将所有分支源代码合并在一起（称为"合并日"），
最终可能造成工作繁琐、耗时，而且需要手动完成。这是因为当一位独立工作的开发人员对应用进行更改时，
有可能会与其他开发人员同时进行的更改发生冲突。如果每个开发人员都自定义自己的本地集成开发环境（IDE），
而不是让团队就一个基于云的 IDE 达成一致，那么就会让问题更加雪上加霜。

持续集成（CI）可以帮助开发人员更加频繁地（有时甚至每天）将代码更改合并到共享分支或"主干"中。
一旦开发人员对应用所做的更改被合并，系统就会通过自动构建应用并运行不同级别的自动化测试（通常是单元测试和集成测试）来验证这些更改，
确保这些更改没有对应用造成破坏。这意味着测试内容涵盖了从类和函数到构成整个应用的不同模块。
如果自动化测试发现新代码和现有代码之间存在冲突，CI 可以更加轻松地快速修复这些错误。

##### CD 持续交付（Continuous Delivery）
完成 CI 中构建及单元测试和集成测试的自动化流程后，持续交付可自动将已验证的代码发布到存储库。
为了实现高效的持续交付流程，务必要确保 CI 已内置于开发管道。持续交付的目标是拥有一个可随时部署到生产环境的代码库。

在持续交付中，每个阶段（从代码更改的合并，到生产就绪型构建版本的交付）都涉及测试自动化和代码发布自动化。在流程结束时，
运维团队可以快速、轻松地将应用部署到生产环境中。

##### CD 持续部署（Continuous Deployment）
对于一个成熟的 CI/CD 管道来说，最后的阶段是持续部署。作为持续交付——自动将生产就绪型构建版本发布到代码存储库——的延伸，
持续部署可以自动将应用发布到生产环境。由于在生产之前的管道阶段没有手动门控，因此持续部署在很大程度上都得依赖精心设计的测试自动化。

实际上，持续部署意味着开发人员对应用的更改在编写后的几分钟内就能生效（假设它通过了自动化测试）。这更加便于持续接收和整合用户反馈。
总而言之，所有这些 CI/CD 的关联步骤都有助于降低应用的部署风险，因此更便于以小件的方式（而非一次性）发布对应用的更改。
不过，由于还需要编写自动化测试以适应 CI/CD 管道中的各种测试和发布阶段，因此前期投资还是会很大。
