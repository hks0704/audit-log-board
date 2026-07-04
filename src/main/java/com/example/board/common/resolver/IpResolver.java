package com.example.board.common.resolver;

import jakarta.servlet.http.HttpServletRequest;

public interface IpResolver {
	String resolve(HttpServletRequest request);
}
