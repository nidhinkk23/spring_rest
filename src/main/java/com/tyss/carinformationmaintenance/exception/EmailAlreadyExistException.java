package com.tyss.carinformationmaintenance.exception;

public class EmailAlreadyExistException extends RuntimeException {
	
	public EmailAlreadyExistException(String msg) {
		super(msg);
	}
}
