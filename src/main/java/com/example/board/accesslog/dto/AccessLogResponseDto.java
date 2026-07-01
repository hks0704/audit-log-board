package com.example.board.accesslog.dto;

import java.time.LocalDateTime;

import com.example.board.accesslog.model.AccessLog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessLogResponseDto {
	private Long id;
	private String clientIp;
	private String requestUri;
	private String userAgent;
	private String httpMethod;
	private LocalDateTime accessTime;

	public AccessLogResponseDto(AccessLog entity) {
		this.id = entity.getId();
		this.clientIp = entity.getClientIp();
		this.requestUri = entity.getRequestUri();
		this.userAgent = entity.getUserAgent();
		this.httpMethod = entity.getHttpMethod();
		this.accessTime = entity.getAccessTime();
	}
}
