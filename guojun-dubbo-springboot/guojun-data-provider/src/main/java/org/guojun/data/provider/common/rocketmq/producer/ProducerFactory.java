package org.guojun.data.provider.common.rocketmq.producer;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.guojun.common.tools.constants.GlobalConstant;
import org.guojun.data.provider.common.exception.ServiceException;
import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * @Description rocketMq生产者
 * @author Guojun
 * @Date 2018年5月13日 下午1:59:04
 *
 */
public class ProducerFactory implements InitializingBean{

	/**
	 * rocketMq生产者,使用DefaultMQProducer
	 */
	private static MQProducer MQ_PRODUCER;
	
	/**
	 * producer的组
	 */
	private String producerGroup = "GUOJUN_PRODUCER_GROUP";

	/**
	 * 实例名称
	 */
	private String instanceName = "GUOJUN_PRODUCER";

	/**
	 * nameServer的地址
	 */
	private String namesrvAddr = "localhost:9876";
	
	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (MQ_PRODUCER == null) {
			/*
			 * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
			 * 注意：ProducerGroupName需要由应用来保证唯一<br>
			 * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
			 * 因为服务器会回查这个Group下的任意一个Producer`
			 */
			DefaultMQProducer defaultMqProducer = new DefaultMQProducer(producerGroup);
			defaultMqProducer.setNamesrvAddr(namesrvAddr);
			defaultMqProducer.setInstanceName(instanceName);

			/*
			 * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
			 * 注意：切记不可以在每次发送消息时，都调用start方法
			 */
			defaultMqProducer.start();

			MQ_PRODUCER = defaultMqProducer;
		}
	}

	/**
	 * 发送消息
	 * @param topic
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public SendResult sendMessage(String topic, String data) throws Exception {
		if (StringUtils.isBlank(data)) {
			throw new ServiceException("发送参数为空！");
		}
		
		Message message = new Message(topic, data.getBytes(GlobalConstant.CHARSET_UTF8));
		return MQ_PRODUCER.send(message);
	}

	/**
	 * 发送消息
	 * @param topic
	 * @param tag
	 * @param data
	 * @return
	 */
	public SendResult sendMessage(String topic, String tag, String data)  throws Exception {
		if (StringUtils.isBlank(data)) {
			throw new ServiceException("发送参数为空！");
		}
		
		if (StringUtils.isBlank(tag)) {
			return this.sendMessage(topic, data);
		}
		
		Message message = new Message(topic, tag, data.getBytes(GlobalConstant.CHARSET_UTF8));
		return MQ_PRODUCER.send(message);
	}
	
	/**
	 * shutdown producer
	 */
	public void shutdown() {
		if (MQ_PRODUCER != null) {
			MQ_PRODUCER.shutdown();
		}
	}
}
