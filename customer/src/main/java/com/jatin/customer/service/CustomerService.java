package com.jatin.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatin.customer.dto.CustomerDto;
import com.jatin.customer.entity.Customer;
import com.jatin.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(Long customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}

	public Customer saveCustomer(CustomerDto customer) {
		return customerRepository.save(Customer.builder().customerName(customer.getCustomerName())
				.customerEmail(customer.getCustomerEmail()).customerBillingAddress(customer.getCustomerBillingAddress())
				.customerShippingAddress(customer.getCustomerShippingAddress()).build());
	}

	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
	}
}
