package org.guojun.data.provider.application.service.met;

import org.apache.rocketmq.client.producer.SendResult;
import org.guojun.common.api.met.IMyMetService;
import org.guojun.common.domain.met.MyMet;
import org.guojun.data.provider.common.exception.ServiceException;
import org.guojun.data.provider.common.rocketmq.enums.TopicEnum;
import org.guojun.data.provider.common.rocketmq.producer.ProducerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;

/**
 * 
 * @Description 发送MyMet消息的服务
 * @author Guojun
 * @Date 2018年5月27日 上午11:47:19
 *
 */
@Service(protocol = "dubbo", loadbalance = "leastactive", timeout = 6000, version = "1.0", retries = 2)
public class MyMetServiceImpl implements IMyMetService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(MyMetServiceImpl.class);
	
	@Autowired
	private ProducerFactory producerFactory;

	@Override
	public String sendMyMet(MyMet myMet) {
		try {
			String myMetJson = JSON.toJSONString(myMet);
			SendResult result = producerFactory.sendMessage(TopicEnum.GUO_JUN_TOPIC.name(), myMetJson);
			return result.toString();
		} catch (Exception e) {
			LOGGER.error("sendMyMet异常:", e);

			throw new ServiceException(e);
		}
	}
}
