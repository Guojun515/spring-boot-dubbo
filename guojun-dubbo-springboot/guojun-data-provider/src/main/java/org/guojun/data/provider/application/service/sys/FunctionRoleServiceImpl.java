package org.guojun.data.provider.application.service.sys;

import java.util.List;

import org.guojun.common.api.sys.IFunctionRoleService;
import org.guojun.data.provider.application.cache.sys.IFunctionRoleCache;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 
 * @Description 功能角色对应关系，做权限使用
 * @author Guojun
 * @Date 2018年5月27日 下午10:57:10
 *
 */
@Service(protocol = "dubbo", loadbalance = "leastactive", timeout = 6000, version = "1.0", retries = 2)
public class FunctionRoleServiceImpl implements IFunctionRoleService {

	@Autowired
	private IFunctionRoleCache functionRoleCache;
	
	@Override
	public List<String> getFunctionUrls() {
		return functionRoleCache.getFunctionUrls();
	}

	@Override
	public List<String> getRoles(String functionUrl) {
		return functionRoleCache.getRoles(functionUrl);
	}

}
