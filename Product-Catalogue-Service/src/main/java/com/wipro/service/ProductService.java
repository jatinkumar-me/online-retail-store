package com.wipro.service;

import java.util.List;

import com.wipro.dto.ProductUpdateDTO;
import com.wipro.entity.Product;

public interface ProductService {
    Product addProduct(Product product);
    void deleteProduct(Long productId);
    Product updateProduct(Long productId, ProductUpdateDTO newDetails);
    Product searchProduct(Long productId);
    List<Product> searchAllProducts();
}
