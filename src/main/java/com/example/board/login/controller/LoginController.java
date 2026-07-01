package com.example.board.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.common.dto.CommonResponse;
import com.example.board.login.dto.LoginRequestDto;
import com.example.board.login.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/v2/login")
@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto,
		HttpServletRequest httpRequest) {

		Long userId = loginService.login(requestDto, httpRequest);

		// 세션 저장 (핵심)
		httpRequest.getSession().setAttribute("USER_ID", userId);

		return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success());
	}
}
