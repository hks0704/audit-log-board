package com.example.board.common.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.board.common.dto.CommonResponse;
import com.example.board.common.exception.CustomNotFoundException;
import com.example.board.common.exception.CustomRateLimitException;
import com.example.board.common.exception.CustomValidateException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<CommonResponse<?>> handlerCustomNotFoundException(CustomNotFoundException e) {
		log.error("CustomExceptionHandler CustomNotFoundException occurred: {}", e.getMessage(), e);
		return ResponseEntity.status(801)
			.body(CommonResponse.error(801));
	}

	@ExceptionHandler(CustomValidateException.class)
	public ResponseEntity<CommonResponse<?>> handlerCustomValidateException(CustomValidateException e) {
		log.error("CustomExceptionHandler CustomValidateException occurred: {}", e.getMessage(), e);
		return ResponseEntity.status(802)
			.body(CommonResponse.error(802));
	}

	@ExceptionHandler(CustomRateLimitException.class)
	public ResponseEntity<CommonResponse<?>> handlerCustomRateLimitException(CustomRateLimitException e) {
		log.error("CustomExceptionHandler CustomRateLimitException occurred: {}", e.getMessage(), e);
		return ResponseEntity.status(803)
			.body(CommonResponse.error(803));
	}



}
