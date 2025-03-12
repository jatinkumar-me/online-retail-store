package com.jatin.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jatin.customer.dto.CustomerDto;
import com.jatin.customer.entity.Customer;
import com.jatin.customer.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto) {
		Customer savedCustomer = customerService.saveCustomer(customerDto);
		return ResponseEntity.ok(savedCustomer);
	}

	@GetMapping("/searchCustomer")
	public ResponseEntity<Customer> getCustomer(@RequestParam Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		return ResponseEntity.ok(customer);
	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		Customer updatedCustomer = customerService.updateCustomer(customer);
		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/deleteCustomer")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
		customerService.deleteCustomer(customerId);
		return ResponseEntity.ok("Customer deleted successfully");
	}
}
