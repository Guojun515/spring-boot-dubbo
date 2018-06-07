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

### 在src/resources/config目录下新增dubbo-spring.xml文件
* 在这里使用的 java config配置，所以在dubbo-spring开启扫描配置就可以
>
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
		xsi:schemaLocation="http://www.springframework.org/schema/beans 
							http://www.springframework.org/schema/beans/spring-beans.xsd
	    					http://www.springframework.org/schema/context    
	    					http://www.springframework.org/schema/context/spring-context-4.0.xsd   
							http://code.alibabatech.com/schema/dubbo         
							http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
		<!-- 加入spring注解扫描 -->
		<context:component-scan base-package="org.guojun.data.provider" />
	</beans>	

