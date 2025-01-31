package com.maybank.labs.starter.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class StarterCustomException extends Exception{

	public StarterCustomException(HttpStatus httpStatus, int customErrorCode, LocalDateTime timestamp, String message) {
		super();
		this.httpStatus = httpStatus;
		this.customErrorCode = customErrorCode;
		this.timestamp = timestamp;
		this.message = message;
	}

	private static final long serialVersionUID = 8600688143018037540L;

	private HttpStatus httpStatus;

	private int customErrorCode;

	private LocalDateTime timestamp;

	private String message;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public int getCustomErrorCode() {
		return customErrorCode;
	}

	public void setCustomErrorCode(int customErrorCode) {
		this.customErrorCode = customErrorCode;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
