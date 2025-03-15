package com.jatin.shopping_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jatin.shopping_service.entity.Product;

@FeignClient(name = "product-service")
public interface ProductClient {

	@GetMapping("/api/products")
	List<Product> getProducts();

	@PostMapping("/api/products")
	Product createProduct(@RequestBody Product product);
}
