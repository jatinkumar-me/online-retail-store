package com.jatin.shopping_service.entity;

import lombok.Data;

@Data
public class CustomerAddress {

	private Long id;
	private String doorNo;
	private String streetName;
	private String layout;
	private String city;
	private String pincode;
}
