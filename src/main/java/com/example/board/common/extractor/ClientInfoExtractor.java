package com.example.board.common.extractor;

import org.springframework.stereotype.Component;

import com.example.board.common.dto.ClientInfo;
import com.example.board.common.resolver.IpResolver;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ClientInfoExtractor {

	private final IpResolver ipResolver;

	public ClientInfoExtractor(IpResolver ipResolver) {
		this.ipResolver = ipResolver;
	}

	public ClientInfo extract(HttpServletRequest request) {

		String clientIp = ipResolver.resolve(request);
		String requestUri = request.getRequestURI();
		String userAgent = request.getHeader("User-Agent");
		String httpMethod = request.getMethod();

		return new ClientInfo(clientIp, requestUri, userAgent, httpMethod);
	}

}
