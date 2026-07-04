package com.example.board.common.resolver;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component("secureIpResolver")
@ConditionalOnProperty(name = "ip.mode", havingValue = "secure")
public class SecureIpResolver implements IpResolver {

	@Override
	public String resolve(HttpServletRequest request) {
		String xff = request.getHeader("X-Forwarded-For");

		if (xff != null && !xff.isBlank()) {
			return xff.split(",")[0].trim(); // 첫 IP만 신뢰
		}

		return request.getRemoteAddr();
	}
}
