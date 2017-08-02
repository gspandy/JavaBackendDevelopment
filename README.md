# 本项目旨在熟悉一些Java后台开发中常用的知识点与技术

本项目采用Intellij Idea开发，使用的的是JDK8，Tomcat7+，mysql。注意：需要将JDK与Tomcat（及与它们相关的内容）调整到与自己的本机相对应。项目所有文件编码均为UTF-8。
从GitHub上面下载或者clone本项目到本地，直接用Intellij Idea打开，然后将各个模块以maven项目添加进来。
![](http://i.imgur.com/93WFFUZ.png)  

## spring模块
本模块使用了aspectj，因此需要下载`AspectJ Support`插件，这样就可以在Intellij Idea编译aspectj文件了。
还需要将Java编译器从Javac改为Ajc,如下图所示：
![](http://i.imgur.com/XfGt33o.jpg)

如果找不到ajpectj对应的class文件，那么就单独对该ajpectj文件进行编译。

spring模块的知识体系如下：
![](http://i.imgur.com/zRPknVh.png)

## springmvc模块
springmvc模块的知识体系如下：
![](http://i.imgur.com/n6sNrPZ.png)

## mybatis模块

mybatis模块的知识体系如下：
![](http://i.imgur.com/uixxGbn.png)

## ssm模块

ssm模块联合spring+springmvc+mybatis构建简单的应用，数据源使用alibaba的druid，二级缓存使用ehcache，日志使用slf4j+log4j，数据库使用mysql。