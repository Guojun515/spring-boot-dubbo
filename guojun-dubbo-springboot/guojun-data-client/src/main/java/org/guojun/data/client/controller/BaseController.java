package org.guojun.data.client.controller;

import org.guojun.data.client.common.dto.ResponseDto;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	
	/**
	 * 返回成功的结果
	 * @param t 需要返回的参数
	 * @return
	 */
	protected <T> ResponseDto<T> success(T t) {
		ResponseDto<T> result = new ResponseDto<T>();
		result.setResult(t);
		return result;
	}
	
	/**
	 * 返回失败的结果
	 * @param errorMsg 失败的原因
	 * @return
	 */
	protected <T> ResponseDto<T> error(String errorMsg) {
		ResponseDto<T> result = new ResponseDto<T>();
		result.setSuccess(false);
		result.setMessage(errorMsg);
		return result;
	}
	
}
