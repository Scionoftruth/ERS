package com.ers.exceptions;

public class InvalidCredentialsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidCredentialsException() {
		super("User Has Provided Invalid Credentials");
	}

}
