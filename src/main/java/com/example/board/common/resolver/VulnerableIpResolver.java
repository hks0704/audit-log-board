package com.example.board.common.resolver;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component("vulnerableIpResolver")
@ConditionalOnProperty(name = "ip.mode", havingValue = "vulnerable")
public class VulnerableIpResolver implements IpResolver {

	@Override
	public String resolve(HttpServletRequest request) {
		return request.getHeader("X-Forwarded-For"); // 그대로 신뢰
	}
}
