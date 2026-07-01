package com.example.board.common.interceptor;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.board.accesslog.service.AccessLogService;
import com.example.board.common.dto.ClientInfo;
import com.example.board.common.extractor.ClientInfoExtractor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccessLogInterceptor implements HandlerInterceptor {

	private final ClientInfoExtractor clientInfoExtractor;
	private final AccessLogService accessLogService;

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler) {

		ClientInfo clientInfo = clientInfoExtractor.extract(request);

		log.info("핸들러가 접속 로그 관련 정보를 입력하였습니다.");

		accessLogService.create(clientInfo);

		return true;
	}

	@Override
	public void afterCompletion(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler,
		@Nullable Exception ex) {

		long startTime = (Long) request.getAttribute("startTime");
		long duration = System.currentTimeMillis()- startTime;

		log.info("time={}ms ip={} uri={}",
			duration,
			request.getRemoteAddr(),
			request.getRequestURI());
	}
}
