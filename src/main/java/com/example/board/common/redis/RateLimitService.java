package com.example.board.common.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.board.common.exception.CustomRateLimitException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RateLimitService {

	// 10초 동안 좋아요 요청 100번

	@Value("${spring.data.redis.time-duration}")
	private Integer timeDuration;

	@Value("${spring.data.redis.maximum-limit}")
	private Integer maximumLimit;


	private static final String KEY_PREFIX = "LIKE:RATE:";

	private final StringRedisTemplate redisTemplate;

	public void checkLimit(String visitorHash) {
		String key = KEY_PREFIX + visitorHash;

		Long count = redisTemplate.opsForValue().increment(key);

		if (Long.valueOf(1L).equals(count)) {
			// redisTemplate.expire(key, Duration.ofSeconds(10));
			redisTemplate.expire(key, Duration.ofSeconds(timeDuration));
		}

		if (count != null && count > maximumLimit) {
			log.warn("Rate limit exceeded for count={}, hash={}", count, visitorHash);
			throw new CustomRateLimitException("Rate limit exceeded for hash: " + visitorHash);
		}

	}

}
