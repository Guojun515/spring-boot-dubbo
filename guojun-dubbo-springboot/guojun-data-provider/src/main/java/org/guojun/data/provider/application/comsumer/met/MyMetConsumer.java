package org.guojun.data.provider.application.comsumer.met;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.message.MessageExt;
import org.guojun.common.tools.constants.GlobalConstant;
import org.guojun.data.provider.common.rocketmq.consumer.builder.IConsumerBuilder;
import org.guojun.data.provider.common.rocketmq.consumer.service.impl.AbstractPushConsumerConcurrently;
import org.guojun.data.provider.common.rocketmq.enums.ConsumerGroupEnum;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description MyMet的消费
 * @author Guojun
 * @Date 2018年5月27日 上午11:56:19
 *
 */
@Service
public class MyMetConsumer extends AbstractPushConsumerConcurrently {
	
	/**
	 * 消费者构造器
	 */				  
	@Resource(name = "defaultMQPushConsumerBuilder")
	private IConsumerBuilder<?> consumerBuilder;

	@Override
	public IConsumerBuilder<?> getConsumerBuilder() {
		return consumerBuilder;
	}

	@Override
	public String getConsumerGroup() {
		return ConsumerGroupEnum.GUO_JUN_CONSUMER_GROUP.name();
	}

	@Override
	public String getTopic() {
		return ConsumerGroupEnum.GUO_JUN_CONSUMER_GROUP.topic();
	}

	@Override
	public void processData(MessageExt messageExt) {
		System.out.println("主题：" + messageExt.getTopic());
		System.out.println("tags：" + messageExt.getTags());
		System.out.println("msgId：" + messageExt.getMsgId());
		System.out.println(StringUtils.toEncodedString(messageExt.getBody(),Charset.forName(GlobalConstant.CHARSET_UTF8)));
	}
}
