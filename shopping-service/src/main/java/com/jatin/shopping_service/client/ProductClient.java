package com.jatin.shopping_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import com.jatin.shopping_service.entity.Product;

@FeignClient(name = "product-service")
public interface ProductClient {

    List<Product> getProducts();

    Product createProduct(Product product);

	
}
