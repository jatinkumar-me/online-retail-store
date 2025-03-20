package com.wipro.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.dto.ProductUpdateDTO;
import com.wipro.entity.Product;
import com.wipro.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        logger.info("Adding new product: {}", product);
        Product savedProduct = productService.addProduct(product);
        logger.info("Product added successfully: {}", savedProduct);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        logger.info("Deleting product with id: {}", id);
        productService.deleteProduct(id);
        String message = "Product id: " + id + " is successfully deleted";
        logger.info(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDTO product) {
        logger.info("Updating product with id: {}", id);
        Product updatedProduct = productService.updateProduct(id, product);
        logger.info("Product updated successfully: {}", updatedProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchProduct(@PathVariable Long id) {
        logger.info("Searching for product with id: {}", id);
        Product product = productService.searchProduct(id);
        logger.info("Product found: {}", product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> searchAllProduct() {
        logger.info("Searching for all products");
        List<Product> allProducts = productService.searchAllProducts();
        logger.info("All products found: {}", allProducts);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
}