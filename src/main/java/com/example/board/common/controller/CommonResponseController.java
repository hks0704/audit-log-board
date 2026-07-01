package com.example.board.common.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.common.dto.CommonResponse;

@RestController
@RequestMapping("/api/v1/common-response")
public class CommonResponseController {
	private static final int NON_STANDARD_SUCCESS_CODE = 700;
	private static final int NON_STANDARD_FAILURE_CODE = 700;
	private static final String NON_STANDARD_SUCCESS_MSG = "Nonstandard success response message.";
	private static final String NON_STANDARD_FAILURE_MSG = "Nonstandard failure response message.";
	private static final Map<String, String> dummyData = Map.of(
		"key1", "value1",
		"key2", "value2",
		"key3", "value3"
	);

	// Success REST API
	@GetMapping("/success")
	public ResponseEntity<CommonResponse<?>> success() {
		return ResponseEntity.status(HttpStatus.OK)
			.body(CommonResponse.success());
	}

	@GetMapping("/success/data")
	public ResponseEntity<CommonResponse<?>> successWithData() {
		return ResponseEntity.status(HttpStatus.OK)
			.body(CommonResponse.success(dummyData));
	}

	@GetMapping("/success/message-data")
	public ResponseEntity<CommonResponse<?>> successWithMessageAndData() {
		return ResponseEntity.status(HttpStatus.OK)
			.body(CommonResponse.success("Custom Message", dummyData));
	}

	@GetMapping("/success/custom")
	public ResponseEntity<CommonResponse<?>> successWithCustom() {
		return ResponseEntity.status(HttpStatus.OK)
			.body(CommonResponse.success(NON_STANDARD_FAILURE_CODE, NON_STANDARD_SUCCESS_MSG, dummyData));
	}

	// Error REST API
	@GetMapping("/error")
	public ResponseEntity<CommonResponse<?>> error() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(CommonResponse.error());
	}

	@GetMapping("/error/status/{status}")
	public ResponseEntity<CommonResponse<?>> errorWithStatus(@PathVariable Integer status) {
		return ResponseEntity.status(status)
			.body(CommonResponse.error(status));
	}

	@GetMapping("/error/custom")
	public ResponseEntity<CommonResponse<?>> errorWithCustom() {
		return ResponseEntity.status(NON_STANDARD_FAILURE_CODE)
			.body(CommonResponse.error(NON_STANDARD_FAILURE_CODE, NON_STANDARD_SUCCESS_MSG));
	}

	@GetMapping("/error/detail")
	public ResponseEntity<CommonResponse<?>> errorWithDetail() {
		return ResponseEntity.status(NON_STANDARD_FAILURE_CODE)
			.body(CommonResponse.error(NON_STANDARD_FAILURE_CODE, NON_STANDARD_SUCCESS_MSG, dummyData));
	}
}
