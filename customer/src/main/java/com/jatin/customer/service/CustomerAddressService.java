package com.jatin.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatin.customer.repository.CustomerAddressRepository;

@Service
public class CustomerAddressService {

	@Autowired
	private CustomerAddressRepository customerAddressRepository;

	public void deleteCustomerAddress(Long customerAddressId) {
		customerAddressRepository.deleteById(customerAddressId);
	}
}
