package com.example.board.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
	private String message;
	private int status;
	private T data;

	public static CommonResponse<Void> success() {
		return CommonResponse.<Void>builder()
			.message("The request was successful")
			.status(200)
			.build();
	}

	public static <T> CommonResponse<T> success(T data) {
		return CommonResponse.<T>builder()
			.message("The request was successful")
			.status(200)
			.data(data)
			.build();
	}

	public static <T> CommonResponse<T> success(String message, T data) {
		return CommonResponse.<T>builder()
			.message(message)
			.status(200)
			.data(data)
			.build();
	}

	public static <T> CommonResponse<T> success(int status, String message, T data) {
		return CommonResponse.<T>builder()
			.message(message)
			.status(status)
			.data(data)
			.build();
	}

	public static CommonResponse<Void> error() {
		return CommonResponse.<Void>builder()
			.message("Internal Server Error: An unexpected error occurred.")
			.status(500)
			.build();
	}

	public static CommonResponse<Void> error(int status) {
		String message = switch (status) {
			case 400 -> "Bad Request: The request is invalid.";
			case 401 -> "Unauthorized: Authentication is required.";
			case 403 -> "Forbidden: Access to the resource is denied.";
			case 404 -> "Not Found: The requested resource could not be found.";
			case 500 -> "Internal Server Error: An unexpected error occurred.";
			default -> "Unexpected Error: An unknown error occurred. Please contact support.";
		};
		return CommonResponse.<Void>builder()
			.message(message)
			.status(status)
			.build();
	}

	public static CommonResponse<Void> error(int status, String message) {
		return CommonResponse.<Void>builder()
			.message(message)
			.status(status)
			.build();
	}

	public static <T> CommonResponse<T> error(int status, String message, T data) {
		return CommonResponse.<T>builder()
			.message(message)
			.status(status)
			.data(data)
			.build();
	}
}
