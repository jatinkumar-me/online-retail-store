package com.jatin.shopping_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatin.shopping_service.dto.InventoryDto;
import com.jatin.shopping_service.entity.Cart;
import com.jatin.shopping_service.entity.Customer;
import com.jatin.shopping_service.entity.CustomerCart;
import com.jatin.shopping_service.entity.Order;
import com.jatin.shopping_service.service.ShoppingService;

@RestController
@RequestMapping("/api/shopping-service")
public class ShoppingController {

	@Autowired
	private ShoppingService shoppingService;

	@GetMapping("/customer/{customerId}/cart")
	public ResponseEntity<Cart> getCart(@PathVariable("customerId") Long customerId) {
		return ResponseEntity.ok(shoppingService.getCart(customerId));
	}

	@PostMapping("/customer")
	public ResponseEntity<CustomerCart> addCustomer(@RequestBody Customer customer) {
		return ResponseEntity.ok(shoppingService.addCustomer(customer));
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable("customerId") Long customerId) {
		return ResponseEntity.ok(shoppingService.getCustomer(customerId));
	}

	@PostMapping("/products")
	public ResponseEntity<Map<String, String>> addProduct(@RequestBody InventoryDto inventoryDto) {
		Map<String, String> response = new HashMap<>();
		response.put("message", shoppingService.createProduct(inventoryDto));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/customer/{customerId}/cart")
	public ResponseEntity<Cart> addItemsToCart(@PathVariable("customerId") Long customerId, @RequestBody Cart cart) {
		return ResponseEntity.ok(shoppingService.addItemsToCart(customerId, cart));
	}

	@PostMapping("/customer/{customerId}/orders")
	public ResponseEntity<Order> placeOrder(@PathVariable("customerId") Long customerId) {
		return ResponseEntity.ok(shoppingService.createOrder(customerId));
	}

	@GetMapping("/customer/{customerId}/orders")
	public ResponseEntity<?> getOrders(@PathVariable("customerId") Long customerId) {
		return ResponseEntity.ok(shoppingService.getAllOrders(customerId));
	}

}
