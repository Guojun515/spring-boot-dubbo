package org.guojun.data.provider.config;

import org.guojun.data.provider.common.redis.JedisConnectionFactoryBean;
import org.guojun.data.provider.common.redis.RedisService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @Description redis 配置
 * @author Guojun
 * @Date 2018年6月3日 下午2:23:07
 *
 */
@Configuration
public class RedisConfig {
	
	//@Bean方法中参数传入其他Bean作为参数，spring会自动注入
	//直接调用@Bean注解的方法，spring会自动注入该方法生成的Bean
	
	/**
	 * redis 链接工厂bean
	 * @param constantConfig
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "jedisConnectionFactory")
	public JedisConnectionFactoryBean jedisConnectionFactoryBean(ConstantConfig constantConfig) {
		JedisConnectionFactoryBean jedisConnectionFactoryBean = new JedisConnectionFactoryBean();
		jedisConnectionFactoryBean.setHost(constantConfig.getRedisHost());
		jedisConnectionFactoryBean.setPort(constantConfig.getRedisPort());
		jedisConnectionFactoryBean.setDbName(constantConfig.getRedisDb());
		jedisConnectionFactoryBean.setPassword(constantConfig.getJdbcPassword());

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(constantConfig.getRedisMaxIdle());
		jedisPoolConfig.setMaxTotal(constantConfig.getRedisMaxActive());
		jedisPoolConfig.setMaxWaitMillis(constantConfig.getRedisMaxWait());
		jedisConnectionFactoryBean.setJedisPoolConfig(jedisPoolConfig);
		
		return jedisConnectionFactoryBean;
	}
	
	/**
	 * redis操作模板，这里采用尽量面向对象的模板
	 * @param jedisConnectionFactory
	 * @return
	 */
	@Bean
	public <K, V> RedisTemplate<K, V> redisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory) {
		
		StringRedisSerializer keySerializer = new StringRedisSerializer();
		GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
		
		RedisTemplate<K, V> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		//键值序列化
		redisTemplate.setKeySerializer(keySerializer);
		//值序列化 
		redisTemplate.setValueSerializer(valueSerializer);
		//哈希键值序列化
		redisTemplate.setHashKeySerializer(keySerializer);
		//哈希值系列化
		redisTemplate.setHashValueSerializer(valueSerializer);
		//开启事务
		redisTemplate.setEnableTransactionSupport(true);
		
		return redisTemplate;
	}
	
	/**
	 * redis服务
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public <T extends Object> RedisService<T> redisService(RedisTemplate<String, T> redisTemplate) {
		RedisService<T> redisService = new RedisService<>();
		redisService.setRedisTemplate(redisTemplate);
		redisService.setKeyPre("guojun:cache");
		
		return redisService;
	}
	
}
