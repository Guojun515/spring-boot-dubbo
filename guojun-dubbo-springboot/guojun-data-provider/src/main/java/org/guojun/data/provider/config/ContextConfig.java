package org.guojun.data.provider.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.guojun.data.provider.common.handler.SpringContextHolder;
import org.guojun.data.provider.common.init.listener.MyApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @EnableAspectJAutoProxy  <aop:aspectj-autoproxy proxy-target-class="true" expose-proxy="true"/>
 * 							AOP动态代理，expose-proxy为true，可以通过AopContext.currentProxy();获取代理类 
 * @Description spring基本基本注解
 * @author Guojun
 * @Date 2018年6月3日 下午1:15:13
 *
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class ContextConfig {
	
	//@Bean方法中参数传入其他Bean作为参数，spring会自动注入
	//直接调用@Bean注解的方法，spring会自动注入该方法生成的Bean

	/**
	 * 线程池配置
	 * @return
	 */
	@Bean(name = "taskExecutor")
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		//核心线程数
		taskExecutor.setCorePoolSize(5);
		//最大线程数
		taskExecutor.setMaxPoolSize(100);
		//允许的空闲时间
		taskExecutor.setKeepAliveSeconds(300);
		//队列的大小
		taskExecutor.setQueueCapacity(20);
		//当任务超过最大线程数是的拒绝方法(CallerRunsPolicy:线程调用运行该任务的 execute 本身)
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		
		return taskExecutor;
	}
	
	/**
	 * 注册静态变量的spring容器
	 * @return
	 */
	@Bean
	public SpringContextHolder springContextHolder() {
		threadPoolTaskExecutor();
		return new SpringContextHolder();
	}
	
	/**
	 * spring监听
	 * @return
	 */
	@Bean
	public MyApplicationListener myApplicationListener() {
		return new MyApplicationListener();
	}
}
