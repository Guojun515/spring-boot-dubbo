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

### provider 配置代码
>
	/**
	 * @DubboComponentScan 指定dubbo扫描路径
	 * @Description dubbo配置
	 * @author Guojun
	 * @Date 2018年6月2日 下午7:39:04
	 *
	 */
	@Configuration
	@DubboComponentScan("org.guojun.data.provider")
	public class DubboConfiguration {
		//@Bean方法中参数传入其他Bean作为参数，spring会自动注入
		//直接调用@Bean注解的方法，spring会自动注入该方法生成的Bean
		/**
		 * 提供方应用信息，用于计算依赖关系
		 * <dubbo:application name="guojun-provider" />
		 * @return
		 */
		@Bean
		public ApplicationConfig applicationConfig() {
			ApplicationConfig applicationConfig = new ApplicationConfig();
			applicationConfig.setName("guojun-provider");
			return applicationConfig;
		}
		/**
		 * 用dubbo协议在20880端口暴露服务
		 * <dubbo:protocol name="dubbo" port="20880" threads="200" accesslog="true"/>
		 * @return
		 */
		@Bean
		public ProtocolConfig protocolConfig() {
			ProtocolConfig protocolConfig = new ProtocolConfig();
			protocolConfig.setName("dubbo");
			protocolConfig.setPort(20880);
			protocolConfig.setThreads(200);
			protocolConfig.setAccesslog("true");
			return protocolConfig;
		}
		/**
		 *  使用multicast广播注册中心暴露服务地址
		 *  <dubbo:registry protocol="zookeeper" file="xxx" address="xxx" />
		 * @param constantConfig 框架会为我们自动注入
		 * @return
		 */
		@Bean
		public RegistryConfig registryConfig(ConstantConfig constantConfig) {
			RegistryConfig registryConfig = new RegistryConfig();
			registryConfig.setProtocol("zookeeper");
			registryConfig.setFile(constantConfig.getSystemOutputFile() + "/output/dubbo.cache");
			registryConfig.setAddress(constantConfig.getZookeeperAddress());
			return registryConfig;
		}
	}

### provider启动程序
>
	import com.alibaba.dubbo.container.Main;
	/**
	 * 
	 * @Description dubbo启动程序
	 * @author Guojun
	 * @Date 2018年6月2日 下午2:11:37
	 *
	 */
	public class ServerApp {
		public static void main(String[] args) {
			Main.main(args);
		}
	}

## 二、dubbo+springboot实现consumer
## 二、dubbo+springboot实现consumer
### 在springboot中采用纯java config配置的方式
* spring boot的配置文件是在src/main/resources的application.properties，可配置springboot相关的参数
>
	#启动的服务端口
	server.port=8888
	#spring boot debug模式，可以看到加载过程
	debug=true

* dubbo consumer配置代码
>
	/**
	 * @DubboComponentScan 指定dubbo扫描的路径
	 * @Description dubbo服务消费方配置
	 * @author Guojun
	 * @Date 2018年6月2日 下午4:55:51
	 *
	 */
	@Configuration
	@DubboComponentScan("org.guojun.data.client")
	public class DubboConfiguration {
		@Autowired
		private ConstantConfig constantConfig;
		/**
		 * 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 
		 * 相当于：<dubbo:application name="dearbinge-parkingspot-consumer" />
		 * @return
		 */
		@Bean
		public ApplicationConfig applicationConfig() {
			ApplicationConfig applicationConfig = new ApplicationConfig();
			applicationConfig.setName("guojun-consummer");
			return applicationConfig;
		}
		/**
		 * 使用zookeeper广播注册中心暴露发现服务地址
		 * 相当于：<dubbo:registry address="zookeeper://localhost:2181" />
		 * @return
		 */
		@Bean
		public RegistryConfig registryConfig() {
			RegistryConfig registryConfig = new RegistryConfig();
			registryConfig.setAddress(constantConfig.getZookeeperAddress());
			return registryConfig;
		}
	}
