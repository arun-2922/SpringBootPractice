package com.evlpract.globalexception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.evlpract.RNF.CustomException;
import com.evlpract.error.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> CustomExceptionHandler(Exception e)
	{
		CustomException ex=(CustomException) e;
		HttpStatus status=ex.getStatus();
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();
        
        //Map<String,String> errorMap= new HashMap<>();
        
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(status,ex.getMessage(),stackTrace),status);
		
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<Map<String, String>>(errorMap,HttpStatus.BAD_REQUEST);
    }
	
	
}
