package com.baeldung.simpleapp.exceptions;

@SuppressWarnings("serial")
public class BookNotFoundException extends RuntimeException {
	 
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

	public BookNotFoundException(String string) {
		super(string);
	}
	
	public BookNotFoundException() {
		super();
	}
}