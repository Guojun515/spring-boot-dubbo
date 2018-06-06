package org.guojun.common.api.met;

import org.guojun.common.domain.met.MyMet;

/**
 * 
 * @Description 发送MyMet消息的服务接口
 * @author Guojun
 * @Date 2018年5月27日 上午11:43:31
 *
 */
public interface IMyMetService {

	/**
	 * 发送消息
	 * @param myMet
	 * @return
	 */
	String sendMyMet(MyMet myMet);
}
