package com.jatin.customer.exception;

public class InvalidCustomerAddressException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCustomerAddressException(String message) {
		super(message);
	}
}

