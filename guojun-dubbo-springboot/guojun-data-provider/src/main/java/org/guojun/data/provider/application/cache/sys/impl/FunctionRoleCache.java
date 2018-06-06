package org.guojun.data.provider.application.cache.sys.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.guojun.data.provider.application
.cache.sys.IFunctionRoleCache;
import org.guojun.data.provider.application.mapper.sys.FunctionRoleMapper;
import org.guojun.data.provider.common.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description 用户信息缓存
 * @author Guojun
 * @Date 2018年5月6日 下午6:26:01
 *
 */
@Service
public class FunctionRoleCache implements IFunctionRoleCache{
	
	private static final String USER_CACHE_KEY = "sys:function:role";
	
	@Autowired
	RedisService<List<String>> redisService;
	
	@Autowired
	private FunctionRoleMapper functionRoleService;

	@Override
	public void initCache() {
		Map<String, List<String>> functionRoles = functionRoleService.queryAllRFunctionRoles();
		if (functionRoles != null && functionRoles.size() > 0) {
			redisService.hmset(USER_CACHE_KEY, functionRoles);
		}
	}


	@Override
	public void updateFunctionRoles() {
		this.initCache();
	}

	@Override
	public List<String> getFunctionUrls() {
		List<String> result = new ArrayList<>();
		
		Map<String, List<String>> functionRoles = redisService.hmget(USER_CACHE_KEY);
		if (functionRoles != null && functionRoles.size() > 0) {
			functionRoles.forEach((functionUrl, roles) -> {
				result.add(functionUrl);
			});
		}
		
		return result;
	}

	@Override
	public List<String> getRoles(String functionUrl) {
		
		return redisService.hmget(USER_CACHE_KEY, functionUrl);
	}
}
