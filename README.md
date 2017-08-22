# qywx
企业微信的项目

## 项目介绍

　　可以简单的把本项目看作[@fastadmin](https://gitee.com/karson/fastadmin)的java实现版本，目前主要实现了用户、角色（群组）和规则（菜单）管理，后续将实现一个企业微信的管理系统。

### 组织结构

```
ssopay-qywx
├── ssopay-qywx-common -- 公共模块，一些工具类及常量定义
├── ssopay-qywx-bean -- 数据库对应的实体类
├── ssopay-qywx-dao -- MyBatis DAO
├── ssopay-qywx-admin -- 后台管理
├── ssopay-qywx-web -- 前台界面，暂未实现
├── ssopay-qywx-doc -- 文档资料
```

### 技术选型

#### 后端技术:
技术 | 名称 | 官网
----|------|----
Spring Framework | 容器  | [http://projects.spring.io/spring-framework/](http://projects.spring.io/spring-framework/)
SpringMVC | MVC框架  | [http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc)
Apache Shiro | 安全框架  | [http://shiro.apache.org/](http://shiro.apache.org/)
MyBatis | ORM框架  | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)
Maven | 项目构建管理  | [http://maven.apache.org/](http://maven.apache.org/)

#### 前端技术:
技术 | 名称 | 官网
----|------|----
jQuery | 函式库  | [http://jquery.com/](http://jquery.com/)
Bootstrap | 前端框架  | [http://getbootstrap.com/](http://getbootstrap.com/)
Bootstrap-table | Bootstrap数据表格  | [http://bootstrap-table.wenzhixin.net.cn/](http://bootstrap-table.wenzhixin.net.cn/)
Font-awesome | 字体图标  | [http://fontawesome.io/](http://fontawesome.io/)

## 项目安装
```
1.导入ssopay-qywx-doc\sql下面的init.sql文件
2.修改ssopay-qywx-admin项目下的jdbc.properties数据库连接
3.然后命令行进入项目根目录运行：mvn clean install
4.将生成的ssopay-qywx-admin\target\ssopay-qywx-admin.war文件部署到tomcat下即可
5.默认用户名：admin 密码：ssopay
```

## 界面演示
### 登录页
![登录页](https://raw.githubusercontent.com/liuyes/ssopay-qywx/master/snapshot/login.png "登录页")
### 控制台
![控制台](https://raw.githubusercontent.com/liuyes/ssopay-qywx/master/snapshot/1.png "控制台")
### 用户管理
![用户管理](https://raw.githubusercontent.com/liuyes/ssopay-qywx/master/snapshot/2.png "用户管理")
![新增修改](https://raw.githubusercontent.com/liuyes/ssopay-qywx/master/snapshot/add.png "新增修改")
### 群组管理
![群组管理](https://raw.githubusercontent.com/liuyes/ssopay-qywx/master/snapshot/3.png "群组管理")
![新增修改](https://raw.githubusercontent.com/liuyes/ssopay-qywx/master/snapshot/add2.png "新增修改")
### 规则管理
![规则管理](https://raw.githubusercontent.com/liuyes/ssopay-qywx/master/snapshot/4.png "规则管理")
![新增修改](https://raw.githubusercontent.com/liuyes/ssopay-qywx/master/snapshot/add3.png "新增修改")

## 许可证

[Apache2](LICENSE "Apache2")

单独使用FastAdmin的模板需要取得相关授权，传送门：[@fastadmin-html](https://gitee.com/karson/fastadmin-html)

