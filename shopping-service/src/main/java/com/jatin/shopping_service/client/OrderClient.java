package com.jatin.shopping_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jatin.shopping_service.entity.Order;

@FeignClient(name = "OrderService")
public interface OrderClient {

	@PostMapping("/api/order")
	Order createOrder(@RequestBody Order order);

	@GetMapping("/api/order/{orderId}")
	Order getOrder(@PathVariable Long orderId);
}
