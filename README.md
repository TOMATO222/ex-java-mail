# ex-java-mail
## 项目结构
- src/main/java：后端代码主体
- frontend:前端代码
    - components ：存放组件，主要就是title和左侧导航栏navbar，Glocal.vue里面存放了公共变量和公共方法
    - router ： 存放路由页面配置
    - views ： 存放页面  
    - src/main/java：后端代码主体
## 说明
+ 不需要配置数据库，远程连接的数据库在服务器上，但是长时间未响应容易断开连接，这个是服务器的问题，还没解决
+ 后端SpringBoot + Mybatis，前端Vue + elementUI，需要Maven、Node.js、NPM环境，安卓需要配置相应的环境
+ 同时调试的话，需要保证手机、电脑在同一个wifi下 cmd界面 ipconfig查看自己的ipv4地址，然后修改application.yaml和Global.vue下面的url
