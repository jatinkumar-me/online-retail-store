package com.jatin.customer.dto;

import com.jatin.customer.entity.CustomerAddress;

import lombok.Data;

@Data
public class CustomerDto {

	private String customerName;

	private String customerEmail;

	private CustomerAddress customerBillingAddress;

	private CustomerAddress customerShippingAddress;
}
