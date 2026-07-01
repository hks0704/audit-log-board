package com.example.board.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.board.common.interceptor.AccessLogInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final AccessLogInterceptor accessLogInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accessLogInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns(
				"/error",
				"/favicon.ico",
				"/css/**",
				"/js/**",
				"/images/**"
			);
		// 실무에서는 API만 로깅
		// registry.addInterceptor(accessLogInterceptor)
		// 	.addPathPatterns("/api/**")
		// 	.excludePathPatterns(
		// 		"/api/auth/**"
		// 	);
	}
}
