package com.evlpract.RNF;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
	
	public CustomException(String message)
	{
		super(message);
	}
	
	public CustomException(HttpStatus status,String message)
	{
		this(message);
		this.status=status;
	}
	

}
