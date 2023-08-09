package com.evlpract.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private String message;
	private int code;
	private String status;
	private String stackTrace;
	
	public ErrorResponse(
            HttpStatus httpStatus,
            String message
    ) {
        
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }
	
	public ErrorResponse(
            HttpStatus httpStatus,
            String message,
            String stackTrace
    ) {
        this(
                httpStatus,
                message        
        );

        this.stackTrace = stackTrace;
    }
}
