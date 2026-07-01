package com.example.board.accesslog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.accesslog.dto.AccessLogResponseDto;
import com.example.board.accesslog.service.AccessLogService;
import com.example.board.common.dto.CommonResponse;
import com.example.board.post.dto.PostDetailResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/admin")
public class AccessLogController {
	private final AccessLogService accessLogService;

	@GetMapping("/access-log")
	public ResponseEntity<CommonResponse<?>> getDetailedAccessLogs() {
		List<AccessLogResponseDto> responseDto = accessLogService.findAllDetails();
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
	}

}
