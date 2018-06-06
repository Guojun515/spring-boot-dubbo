package org.guojun.data.provider.application.service.sys;

import java.util.List;

import org.guojun.common.api.sys.ISysUserService;
import org.guojun.common.domain.sys.SysUser;
import org.guojun.data.provider.application
.mapper.sys.SysUserMapper;
import org.guojun.data.provider.common.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

@Service(protocol = "dubbo", loadbalance = "leastactive", timeout = 6000, version = "1.0", retries = 2)
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements ISysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public List<SysUser> queryUserRoles(SysUser queryParam) {
		return sysUserMapper.queryUserRoles(queryParam);
	}
	
}
