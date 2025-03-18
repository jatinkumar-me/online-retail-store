package com.jatin.shopping_service.entity;

import lombok.Data;

@Data
public class Customer {

	private Long id;
	private String customerName;
	private String customerEmail;
	private CustomerAddress customerBillingAddress;
	private CustomerAddress customerShippingAddress;
}
