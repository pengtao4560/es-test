[本项目直接使用jdk17进行对 java12,java13新特性的学习](尚硅谷_宋红康_深入解读Java12&13新特性.pdf)
可能出现的问题
【java: JPS incremental annotation processing is disabled. Compilation results on partial recompilation may be inaccurate.
Use build process "jps.track.ap.dependencies" VM flag to enable/disable incremental annotation processing environment.】

fix:    pom文件 去掉lombok或者 升级lombok升级到1.18.20后成功解决此问题

【无效的目标发行版：17 】
fix:    将 Settings --> Build, Execution, Deployment --> Complier -->Java Complier 配置下的
Project bytecode version: 17 改为 与 项目使用的 JDK 版本一样即可。

Java 11 以及之前版本中，Switch 表达式支持下面类型：
byte、char、short、int、Byte、Character、Short、
Integer、enum、String，在未来的某个 Java 版本有可能会允许支持 float、double 和 long 
（以及对应类型的包装 类型）。
