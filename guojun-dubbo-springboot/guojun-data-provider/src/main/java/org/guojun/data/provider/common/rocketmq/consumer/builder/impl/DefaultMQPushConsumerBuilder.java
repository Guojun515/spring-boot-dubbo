package org.guojun.data.provider.common.rocketmq.consumer.builder.impl;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.guojun.data.provider.common.rocketmq.consumer.builder.IConsumerBuilder;

/**
 * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
 * 但是实际PushConsumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法<br>
 * @Description 构造DefaultMQPushConsumer
 * @author Guojun
 * @Date 2018年5月13日 下午8:46:23
 *
 */
public class DefaultMQPushConsumerBuilder extends AbstractConsumerBuilder<MQPushConsumer> implements IConsumerBuilder<MQPushConsumer> {
	
	@Override
	public MQPushConsumer build(String consumerGroup) {
		DefaultMQPushConsumer mqConsumer = new DefaultMQPushConsumer(consumerGroup);
		mqConsumer.setNamesrvAddr(this.getNamesrvAddr());
		mqConsumer.setInstanceName(DefaultMQPushConsumerBuilder.class.getSimpleName());
		
		return mqConsumer;
	}

}
