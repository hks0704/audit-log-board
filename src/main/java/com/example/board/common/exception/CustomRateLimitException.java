package com.example.board.common.exception;

public class CustomRateLimitException extends RuntimeException {

	public CustomRateLimitException() {
		super("The request rate limit exceeded.");
	}

	public CustomRateLimitException(String message) {
		super(message);
	}

	public CustomRateLimitException(String message, Throwable cause) {
		super(message, cause);
	}
}
