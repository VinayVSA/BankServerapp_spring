package com.cg.bsappl.exception;

public class AccountException extends Exception{

	public AccountException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AccountException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AccountException(String message) {
		super(message);
		System.out.println(message);
	}

	public AccountException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
