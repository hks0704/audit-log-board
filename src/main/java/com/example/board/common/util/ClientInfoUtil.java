package com.example.board.common.util;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ClientInfoUtil {

	public String getClientIp(HttpServletRequest request) {
		return request.getRemoteAddr();
	}

}
