package com.wipro.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.Exception.ProductNotFoundException;
import com.wipro.dto.ProductUpdateDTO;
import com.wipro.entity.Product;
import com.wipro.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        logger.info("Adding new product: {}", product);
        Product savedProduct = productRepository.save(product);
        logger.info("Product added successfully: {}", savedProduct);
        return savedProduct;
    }

    @Override
    public void deleteProduct(Long productId) {
        logger.info("Deleting product with id: {}", productId);
        productRepository.deleteById(productId);
        logger.info("Product deleted successfully with id: {}", productId);
    }

    @Override
    public Product updateProduct(Long productId, ProductUpdateDTO newDetails) {
        logger.info("Updating product with id: {}", productId);
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            if (newDetails.getProductName() != null) {
                existingProduct.setProductName(newDetails.getProductName());
            }
            if (newDetails.getProductDescription() != null) {
                existingProduct.setProductDescription(newDetails.getProductDescription());
            }
            if (newDetails.getProductPrice() != null) {
                existingProduct.setProductPrice(newDetails.getProductPrice());
            }
            Product updatedProduct = productRepository.save(existingProduct);
            logger.info("Product updated successfully: {}", updatedProduct);
            return updatedProduct;
        }
        logger.error("Product not found with id: {}", productId);
        throw new ProductNotFoundException("Product not found!");
    }

    @Override
    public Product searchProduct(Long productId) {
        logger.info("Searching for product with id: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        logger.info("Product found: {}", product);
        return product;
    }

    @Override
    public List<Product> searchAllProducts() {
        logger.info("Searching for all products");
        List<Product> allProducts = productRepository.findAll();
        logger.info("All products found: {}", allProducts);
        return allProducts;
    }
}