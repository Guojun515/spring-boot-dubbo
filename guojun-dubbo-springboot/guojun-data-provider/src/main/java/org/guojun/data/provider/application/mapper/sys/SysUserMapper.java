package org.guojun.data.provider.application.mapper.sys;

import java.util.List;

import org.guojun.common.domain.sys.SysUser;

import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * @Description 用户管理
 * @author Guojun
 * @Date 2018年3月29日 下午11:22:54
 *
 */
public interface SysUserMapper extends Mapper<SysUser> {

	/**
	 * 获取用户信息，一并获取角色信息
	 * @param queryParam
	 * @return
	 */
	public List<SysUser> queryUserRoles(SysUser queryParam);
}
