package org.guojun.data.client.controller.sys;

import org.guojun.common.api.sys.ISysVmInfoService;
import org.guojun.common.domain.sys.SysVmInfo;
import org.guojun.data.client.common.dto.ResponseDto;
import org.guojun.data.client.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 
 * @Description 获取系统的内部信息
 * @author Guojun
 * @Date 2018年4月5日 下午3:16:05
 *
 */
@Controller
@RequestMapping("/vm")
public class SysVmInfoController extends BaseController {
	
	@Reference(version = "1.0")
	private ISysVmInfoService sysVmInfoService;
	
	@RequestMapping(value = "/getVmConfig", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<SysVmInfo> getSysConfig(){
		SysVmInfo sysVmInfo = sysVmInfoService.getSysVmConfig();
		return this.success(sysVmInfo);
	}
}
