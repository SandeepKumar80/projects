package com.org.service.user.controller;

import javax.transaction.SystemException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.org.service.user.exception.PageSizeTooLarge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PageSizeTooLarge.class)
	public ResponseEntity<String> handleMissingRequiredParameter(PageSizeTooLarge exception) {
		log.warn("Page Size Too Large parameter={}", exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SystemException.class)
	public ResponseEntity<String> handleSystemException(SystemException exception) {
		log.error("System error={}", exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
