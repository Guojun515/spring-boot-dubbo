package org.guojun.data.provider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Order(1)：让配置文件首先加载
 * @Description 配置文件
 * @author Guojun
 * @Date 2018年6月3日 上午11:59:15
 *
 */
@Configuration
@PropertySource("classpath:properties/global.properties")
public class ConstantConfig {

	@Value("${system.out.file}")
	private String systemOutputFile;
	
	@Value("${zookeeper.address}")
	private String zookeeperAddress;
	
	@Value("${jdbc.username}")
	private String jdbcUserName;
	
	@Value("${jdbc.password}")
	private String jdbcPassword;
	
	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;
	
	@Value("${jdbc.url}")
	private String jdbcUrl;
	
	@Value("${jdbc.maxActive}")
	private int jdbcMaxActive;
	
	@Value("${jdbc.initialSize}")
	private int jdbcInitialSize;
	
	@Value("${jdbc.maxWait}")
	private long jdbcMaxWait;
	
	@Value("${jdbc.minIdle}")
	private int jdbcMinIdle;
	
	@Value("${jdbc.timeBetweenEvictionRunsMillis}")
	private long jdbcTimeBetweenEvictionRunsMillis;
	
	@Value("${jdbc.minEvictableIdleTimeMillis}")
	private long jdbcMinEvictableIdleTimeMillis;
	
	@Value("${redis.host}")
	private String redisHost;
	
	@Value("${redis.port}")
	private int redisPort;
	
	@Value("${redis.db}")
	private int redisDb;
	
	@Value("${redis.password}")
	private String redisPassword;
	
	@Value("${redis.maxIdle}")
	private int redisMaxIdle;
	
	@Value("${redis.maxActive}")
	private int redisMaxActive;
	
	@Value("${redis.maxWait}")
	private long redisMaxWait;
	
	@Value("${nameserver.host}")
	private String nameServerHost;

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

	/**
	 * 数据库用户名
	 * @return
	 */
	public String getJdbcUserName() {
		return jdbcUserName;
	}

	/**
	 * 数据库密码
	 * @return
	 */
	public String getJdbcPassword() {
		return jdbcPassword;
	}

	/**
	 * 数据库驱动
	 * @return
	 */
	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}

	/**
	 * 数据库连接地址
	 * @return
	 */
	public String getJdbcUrl() {
		return jdbcUrl;
	}

	/**
	 * 连接池最大使用连接数量
	 * @return
	 */
	public int getJdbcMaxActive() {
		return jdbcMaxActive;
	}

	/**
	 * 初始化大小  
	 * @return
	 */
	public int getJdbcInitialSize() {
		return jdbcInitialSize;
	}

	/**
	 * 获取连接最大等待时间
	 * @return
	 */
	public long getJdbcMaxWait() {
		return jdbcMaxWait;
	}

	/**
	 * 连接池最小空闲 
	 * @return
	 */
	public int getJdbcMinIdle() {
		return jdbcMinIdle;
	}

	/**
	 * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
	 * @return
	 */
	public long getJdbcTimeBetweenEvictionRunsMillis() {
		return jdbcTimeBetweenEvictionRunsMillis;
	}

	/**
	 * 配置一个连接在池中最小生存的时间，单位是毫秒 
	 * @return
	 */
	public long getJdbcMinEvictableIdleTimeMillis() {
		return jdbcMinEvictableIdleTimeMillis;
	}

	/**
	 * redis的访问地址
	 * @return
	 */
	public String getRedisHost() {
		return redisHost;
	}

	/**
	 * redis的访问端口
	 * @return
	 */
	public int getRedisPort() {
		return redisPort;
	}

	/**
	 * redis访问的库
	 * @return
	 */
	public int getRedisDb() {
		return redisDb;
	}

	/**
	 * redis的访问密码
	 * @return
	 */
	public String getRedisPassword() {
		return redisPassword;
	}

	/**
	 * 最大空闲数，数据库连接的最大空闲时间。超过空闲时间，数据库连接将被标记为不可用，然后被释放。设为0表示无限制。
	 * @return
	 */
	public int getRedisMaxIdle() {
		return redisMaxIdle;
	}

	/**
	 * 连接池的最大数据库连接数。设为0表示无限制
	 * @return
	 */
	public int getRedisMaxActive() {
		return redisMaxActive;
	}

	/**
	 * 最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。
	 * @return
	 */
	public long getRedisMaxWait() {
		return redisMaxWait;
	}

	/**
	 * rocketmq的nameserver的地址
	 * @return
	 */
	public String getNameServerHost() {
		return nameServerHost;
	}
	
}
