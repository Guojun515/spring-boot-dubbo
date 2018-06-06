package org.guojun.data.provider.application.cache.init;

import java.util.Map;

import org.guojun.data.provider.application
.cache.ICacheService;
import org.guojun.data.provider.common.init.ISpringInitService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description 初始化缓存
 * @author Guojun
 * @Date 2018年5月6日 下午6:30:35
 *
 */
@Component
public class CacheInitServiceImpl implements ISpringInitService, InitializingBean {
	
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
		Map<String, ICacheService> cacheServices = context.getBeansOfType(ICacheService.class);
		if (cacheServices != null) {
			cacheServices.forEach((key, cacheService) -> {
				if (cacheService.isStartupInitCache()) {
					taskExecutor.execute(()->{
						cacheService.initCache();
					});
				}
			});
		}
	}
}
