package com.jatin.shopping_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jatin.shopping_service.entity.Cart;

@FeignClient(name = "cart-service")
public interface CartClient {

	@GetMapping("/api/cart/{id}")
	public Cart getCart(@PathVariable Long id);

	@PostMapping("/api/cart")
    public Cart addCart(@RequestBody Cart cart);

	@PutMapping("/api/cart/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart cart);

	@DeleteMapping("/api/cart/{id}")
    public void emptyCart(@PathVariable Long id);
}
