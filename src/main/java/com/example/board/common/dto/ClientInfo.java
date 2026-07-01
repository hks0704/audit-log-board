package com.example.board.common.dto;

public record ClientInfo (
	String clientIp,
	String requestUri,
	String userAgent,
	String httpMethod
) {}
