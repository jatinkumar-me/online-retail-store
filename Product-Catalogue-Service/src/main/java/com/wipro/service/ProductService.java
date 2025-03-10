package com.wipro.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Product;
import com.wipro.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Long productId, Product newDetails) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setProductName(newDetails.getProductName());
            existingProduct.setProductDescription(newDetails.getProductDescription());
            existingProduct.setProductPrice(newDetails.getProductPrice());
            return productRepository.save(existingProduct);
        }
        throw new RuntimeException("Product not found!");
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
    }
    public List<Product> getAllProduct(){
    	return productRepository.findAll();
    }
}