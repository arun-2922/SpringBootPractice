package com.externalAPIWC.customexception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
	public CustomException(String message) {
		super(message);
	}
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CustomException(HttpStatus status,String message) {
		this(message);
		this.status = status;
	}
}
