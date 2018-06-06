package org.guojun.data.provider.config;

import javax.sql.DataSource;

import org.guojun.data.provider.common.job.MyJobFactory;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 
 * @Description job相关的配置
 * @author Guojun
 * @Date 2018年6月3日 下午2:04:26
 *
 */
@Configuration
public class JobConfig {

	//@Bean方法中参数传入其他Bean作为参数，spring会自动注入
	//直接调用@Bean注解的方法，spring会自动注入该方法生成的Bean
	
	/**
	 * quartz job实现spring注入
	 * @return
	 */
	@Bean(name = "myJobFactory")
	public JobFactory myJobFactory() {
		return new MyJobFactory();
	}

	/**
	 * quartz与spring结合配置
	 * @param dataSource
	 * @param jobFactory
	 * @return
	 */
	@Bean(name = "quartzScheduler")
	public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource") DataSource dataSource, @Qualifier("myJobFactory") JobFactory myJobFactory ) {
		//资源路径解析器
		PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		//数据源
		schedulerFactoryBean.setDataSource(dataSource);
		//自动启动
		schedulerFactoryBean.setAutoStartup(true);
		//Scheduler的key
		schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
		//quartz配置文件
		schedulerFactoryBean.setConfigLocation(resourcePatternResolver.getResourceLoader().getResource("classpath:properties/quartz.properties"));
		//quartz实现spring注入
		schedulerFactoryBean.setJobFactory(myJobFactory);
		
		return schedulerFactoryBean;
	}
}
