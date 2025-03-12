package com.jatin.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatin.customer.dto.CustomerDto;
import com.jatin.customer.entity.Customer;
import com.jatin.customer.exception.ResourceNotFoundException;
import com.jatin.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
	}

	public Customer saveCustomer(CustomerDto customer) {
		return customerRepository.save(Customer.builder().customerName(customer.getCustomerName())
				.customerEmail(customer.getCustomerEmail()).customerBillingAddress(customer.getCustomerBillingAddress())
				.customerShippingAddress(customer.getCustomerShippingAddress()).build());
	}

	public Customer updateCustomer(Customer updatedCustomer) {
		return customerRepository.findById(updatedCustomer.getId())
				.map(existingCustomer -> {
					if (updatedCustomer.getCustomerName() != null) {
						existingCustomer.setCustomerName(updatedCustomer.getCustomerName());
					}
					if (updatedCustomer.getCustomerEmail() != null) {
						existingCustomer.setCustomerEmail(updatedCustomer.getCustomerEmail());
					}
					if (updatedCustomer.getCustomerBillingAddress() != null) {
						existingCustomer.setCustomerBillingAddress(updatedCustomer.getCustomerBillingAddress());
					}
					if (updatedCustomer.getCustomerShippingAddress() != null) {
						existingCustomer.setCustomerShippingAddress(updatedCustomer.getCustomerShippingAddress());
					}
					return customerRepository.save(existingCustomer);
				})
				.orElseThrow(
						() -> new ResourceNotFoundException("Customer not found with ID: " + updatedCustomer.getId()));
	}

	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
	}
}
