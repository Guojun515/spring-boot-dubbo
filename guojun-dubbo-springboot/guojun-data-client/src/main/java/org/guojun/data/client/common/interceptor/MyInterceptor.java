package org.guojun.data.client.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义Spring MVC拦截器
 * @author v-yuguojun
 *
 */
public class MyInterceptor implements HandlerInterceptor {

	/**
	 * 完成请求的拦截
	 * @param handler 请求的目标方法
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		
	}

	/**
	 * 对返回的视图处理，在DispatcherServlet呈现视图之前
	 * @param handler 请求的目标方法
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
	}

	/**
	 * 请求前的处理
	 * @param handler 请求的目标方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String requestPath = request.getRequestURI();
		System.out.println("请求地址" + requestPath);
		
		return true;
	}
}
