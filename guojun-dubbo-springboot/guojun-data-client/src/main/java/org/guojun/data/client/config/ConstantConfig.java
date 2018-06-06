package org.guojun.data.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:constant.properties")
public class ConstantConfig {

	@Value("${system.out.file}")
	private String systemOutputFile;
	
	@Value("${zookeeper.address}")
	private String zookeeperAddress;

	/**
	 * 文件输出目录
	 * @return
	 */
	public String getSystemOutputFile() {
		return systemOutputFile;
	}

	/**
	 * zookeeper地址
	 * @return
	 */
	public String getZookeeperAddress() {
		return zookeeperAddress;
	}
}
