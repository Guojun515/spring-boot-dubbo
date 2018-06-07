# spring-boot-dubbo
## 一、dubbo+spring实现provider
### 在src/resources目录下新增dubbo.properties文件
* 使用com.alibaba.dubbo.container.Main的main方法来启动provider，需要在dubbo.properties文件中配置如下信息
>
	#dubbo启动容器(不设置默认spring一个)
	dubbo.container = spring,logback
	#dubbo spring配置（通过Main.main方法启动需要通过此参数配置spring配置文件）
	dubbo.spring.config = classpath:/config/dubbo-spring.xml
	#优雅停机
	dubbo.shutdown.hook = true
	#dubbo日志容器配置
	dubbo.logback.file = E:/data/provider/log/dubbo.log
	dubbo.logback.level = DEBUG
	dubbo.logback.maxhistory = 100
