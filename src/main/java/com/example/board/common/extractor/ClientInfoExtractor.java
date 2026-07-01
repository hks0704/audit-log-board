package com.example.board.common.extractor;

import org.springframework.stereotype.Component;

import com.example.board.common.dto.ClientInfo;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ClientInfoExtractor {

	public ClientInfo extract(HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		String requestUri = request.getRequestURI();
		String userAgent = request.getHeader("User-Agent");
		String httpMethod = request.getMethod();

		return new ClientInfo(clientIp, requestUri, userAgent, httpMethod);
	}

	private String resolveIp(HttpServletRequest request) {
		String xff = request.getHeader("X-Forwarded-For");

		if (xff != null && !xff.isBlank()) {
			return xff.split(",")[0].trim(); // 첫 IP만 신뢰
		}

		return request.getRemoteAddr();
	}
}
