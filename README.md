学习 哔哩哔哩尚硅谷 springcloud课程、java8新特性、nginx、linux

未来：redis、mongo、消息队列、mysql进阶、
## markdown文件表情

语法： &#xCODE;

其中，CODE 可以从 Emoji Unicode Tables 中查到。

[Emoji Unicode Tables](https://apps.timwhitlock.info/emoji/tables/unicode#block-4-enclosed-characters)

例子： 查到了 表情对应的 Unicode 编码为 U+1F34E，则与此表情对应的 CODE 为 1F34E (舍弃前面的 U+)。
我们只需在 Markdown 文档中输入 &#x1F34E; 即可显示为 。

&#x1F64B;

🙋



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
























