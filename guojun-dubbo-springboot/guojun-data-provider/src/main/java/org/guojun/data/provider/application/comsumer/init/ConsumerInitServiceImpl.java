package org.guojun.data.provider.application.comsumer.init;

import java.util.Map;

import org.guojun.data.provider.common.init.ISpringInitService;
import org.guojun.data.provider.common.rocketmq.consumer.service.IPushConsumer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description 启动consummer消费
 * @author Guojun
 * @Date 2018年5月27日 下午1:01:31
 *
 */
@Component
public class ConsumerInitServiceImpl implements ISpringInitService, InitializingBean {

	@Autowired
	ThreadPoolTaskExecutor taskExecutor;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if (taskExecutor == null) {
			taskExecutor = new ThreadPoolTaskExecutor();
		}
	}

	@Override
	public void init(ApplicationContext context) {
		Map<String, IPushConsumer> pushConsumers = context.getBeansOfType(IPushConsumer.class);
		if (pushConsumers != null && pushConsumers.size() > 0) {
			pushConsumers.forEach((beanName, pushConsumer) -> {
				taskExecutor.execute(() -> {
					pushConsumer.startConsumption();
				});
			});
		}
	}

}
