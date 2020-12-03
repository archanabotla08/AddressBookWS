package com.blz.addressbookworkshop;

public class AddressBookException extends Exception{

	enum ExceptionType {
		DATABASE_EXCEPTION, NO_SUCH_CLASS
	}

	public ExceptionType type;
	public String message;
	
	public AddressBookException(String message) {
		this.message = message;
	}

	public AddressBookException(String message, ExceptionType type) {
		this.message=message;
		this.type = type;
	}

}
