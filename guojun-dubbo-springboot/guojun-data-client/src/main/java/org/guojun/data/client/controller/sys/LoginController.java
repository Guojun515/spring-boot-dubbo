package org.guojun.data.client.controller.sys;

import java.util.List;

import org.guojun.common.api.sys.ISysUserService;
import org.guojun.common.domain.sys.SysUser;
import org.guojun.data.client.common.dto.ResponseDto;
import org.guojun.data.client.controller.BaseController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 
 * @Description 
 * @author Guojun
 * @Date 2018年4月5日 下午3:16:05
 *
 */
@Controller
@RequestMapping("/user")
public class LoginController extends BaseController {
	
	@Reference(version = "1.0")
	private ISysUserService sysUserService;
	
	@RequestMapping(value = "/gotoLogin")
	@ResponseBody
	public ResponseDto<String> gotoLogin(){
		return this.error("请登录");
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<List<SysUser>> info(){
		List<SysUser> users = sysUserService.selectAll();
		return this.success(users);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto<SysUser> create(SysUser sysUser){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
		SysUser user = sysUserService.insert(sysUser);
		return this.success(user);
	}
}
