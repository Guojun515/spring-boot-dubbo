package org.guojun.common.api.sys;

import java.util.List;

/**
 * 
 * @Description 功能角色对应关系，做权限使用
 * @author Guojun
 * @Date 2018年5月27日 下午9:42:22
 *
 */
public interface IFunctionRoleService {
	
	/**
	 * 获取缓存的地址
	 * @return
	 */
	public List<String> getFunctionUrls();
	
	/**
	 * 通过URL获取对应的角色信息
	 * @param userId
	 * @return
	 */
	public List<String> getRoles(String functionUrl);
}
