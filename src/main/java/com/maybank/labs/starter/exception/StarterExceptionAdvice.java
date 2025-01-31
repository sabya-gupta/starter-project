package com.maybank.labs.starter.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StarterExceptionAdvice {

	@ExceptionHandler(StarterCustomException.class)
	public ResponseEntity<StarterCustomException> globalExceptionHandler(StarterCustomException ex) {

		return new ResponseEntity<StarterCustomException>(ex, ex.getHttpStatus());
	}

}
