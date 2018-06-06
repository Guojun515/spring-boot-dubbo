package org.guojun.common.api.sys;

import java.util.List;

import org.guojun.common.api.IBaseService;
import org.guojun.common.domain.sys.SysUser;

public interface ISysUserService extends IBaseService<SysUser> {
	
	/**
	 * 获取用户信息，一并获取角色信息
	 * @param queryParam
	 * @return
	 */
	public List<SysUser> queryUserRoles(SysUser queryParam);
}
