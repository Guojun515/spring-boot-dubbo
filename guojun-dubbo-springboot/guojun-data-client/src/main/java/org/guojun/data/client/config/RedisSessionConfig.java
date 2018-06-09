package org.guojun.data.client.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * redisNamespace: session 缓存的key
 * maxInactiveIntervalInSeconds: session过期时间
 * @Description 使用 spring session + redis
 * @author Guojun
 * @Date 2018年6月9日 下午12:14:52
 *
 */
@EnableRedisHttpSession(redisNamespace = "guojun:spring", maxInactiveIntervalInSeconds = 3600)
public class RedisSessionConfig {

}