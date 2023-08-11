package com.externalAPIWC.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.externalAPIWC.Error.ErrorResp;
import com.externalAPIWC.customexception.CustomException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResp> customExceptionHandler(Exception e)
	{
		CustomException ex=(CustomException) e;
		HttpStatus status=ex.getStatus();
		
		return new ResponseEntity<ErrorResp>(new ErrorResp(status.value(),ex.getMessage()),status);
	}
}
