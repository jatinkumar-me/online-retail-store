package com.jatin.shopping_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jatin.shopping_service.entity.Customer;

@FeignClient(name = "customer-service")
public interface CustomerClient {

	@GetMapping("/api/searchCustomer")
	public Customer getCustomer(@RequestParam Long customerId);

	@PostMapping("/api/addCustomer")
	public Customer addCustomer(@RequestBody Customer customer);
}
