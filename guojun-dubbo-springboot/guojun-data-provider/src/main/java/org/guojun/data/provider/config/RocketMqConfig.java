package org.guojun.data.provider.config;

import org.guojun.data.provider.common.rocketmq.consumer.builder.impl.DefaultMQPushConsumerBuilder;
import org.guojun.data.provider.common.rocketmq.producer.ProducerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @Description rocketmq配置
 * @author Guojun
 * @Date 2018年6月3日 下午6:26:39
 *
 */
@Configuration
public class RocketMqConfig {
	
	//@Bean方法中参数传入其他Bean作为参数，spring会自动注入
	//直接调用@Bean注解的方法，spring会自动注入该方法生成的Bean

	/**
	 * 消息发送工厂
	 * @param constantConfig
	 * @return
	 */
	@Bean
	public ProducerFactory producerFactory(ConstantConfig constantConfig) {
		ProducerFactory producerFactory = new ProducerFactory();
		producerFactory.setNamesrvAddr(constantConfig.getNameServerHost());
		
		return producerFactory;
	}
	
	/**
	 * 消费者构建器
	 * @param constantConfig
	 * @return
	 */
	@Bean(name = "defaultMQPushConsumerBuilder")
	public DefaultMQPushConsumerBuilder consumerBuilder(ConstantConfig constantConfig) {
		DefaultMQPushConsumerBuilder consumerBuilder = new DefaultMQPushConsumerBuilder();
		consumerBuilder.setNamesrvAddr(constantConfig.getNameServerHost());
		
		return consumerBuilder;
	}
}
