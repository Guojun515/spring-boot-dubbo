package org.guojun.data.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

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
