package org.guojun.data.client.controller.met;

import org.guojun.common.api.met.IMyMetService;
import org.guojun.common.domain.met.MyMet;
import org.guojun.data.client.common.dto.ResponseDto;
import org.guojun.data.client.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 
 * @Description MyMetController
 * @author Guojun
 * @Date 2018年5月27日 下午1:08:21
 *
 */
@Controller
@RequestMapping("/myMet")
public class MyMetController extends BaseController {
	
	@Reference(version = "1.0")
	private IMyMetService myMetService;
	
	@RequestMapping(value = "/sendMyMet", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto<String> sendMyMet(@RequestBody MyMet myMet) {
		String result = myMetService.sendMyMet(myMet);
		return this.success(result);
	}
}
