package org.guojun.data.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

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
