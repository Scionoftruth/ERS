package com.ers.exceptions;

public class AccountDoesNotExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AccountDoesNotExistException() {
		super("Account Does Not Exist");
	}

}
