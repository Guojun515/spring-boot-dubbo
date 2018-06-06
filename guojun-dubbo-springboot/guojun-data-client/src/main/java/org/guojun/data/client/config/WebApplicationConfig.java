package org.guojun.data.client.config;

import org.guojun.data.client.common.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @Description Spring MVC 配置
 * @author Guojun
 * @Date 2018年6月4日 下午11:32:38
 *
 */
@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
	}
	
}
