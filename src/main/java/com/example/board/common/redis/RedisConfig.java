package com.example.board.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

// Spring Boot 3.2 기준
// LettuceConnectionFactory 자동 생성
@Configuration
//@EnableRedisRepositories(enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP) // 단순 key-value 저장시에는 생략
public class RedisConfig {

	@Bean
	RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, String> template = new RedisTemplate<>();

		template.setConnectionFactory(factory);

		return template;

	}
}
