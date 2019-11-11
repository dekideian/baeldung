package com.baeldung.simpleapp.exceptions;

@SuppressWarnings("serial")
public class BookIdMismatchException extends RuntimeException {
	 
    public BookIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

	public BookIdMismatchException(String string) {
		super(string);
	}
	
	public BookIdMismatchException() {
		super();
	}
}