package org.guojun.data.provider.application.service.sys;


import org.guojun.common.api.sys.ISysRoleService;
import org.guojun.common.domain.sys.SysRole;
import org.guojun.data.provider.common.service.BaseServiceImpl;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 
 * @Description 角色相关
 * @author Guojun
 * @Date 2018年5月27日 下午9:44:47
 *
 */
@Service(protocol = "dubbo", loadbalance = "leastactive", timeout = 6000, version = "1.0", retries = 2)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements ISysRoleService{
	
	
}
