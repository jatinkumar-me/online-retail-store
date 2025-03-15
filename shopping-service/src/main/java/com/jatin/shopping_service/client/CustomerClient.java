package com.jatin.shopping_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jatin.shopping_service.entity.Customer;

@FeignClient(name = "customer-service")
public interface CustomerClient {

	@GetMapping("/api/searchCustomer/{customerId}")
	public Customer getCustomer(@PathVariable("customerId") Long userId);

	@PostMapping("/api/addCustomer")
	public Customer addCustomer(@RequestBody Customer  customer);
}
