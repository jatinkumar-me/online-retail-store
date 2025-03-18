package com.capstone.cart_service.service;

import com.capstone.cart_service.entity.Cart;
import com.capstone.cart_service.entity.LineItem;
import com.capstone.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart addCart(Cart cart) {
//        if(cart.getLineItems() == null){
//            cart.setLineItems(new ArrayList<>());
//        }
        return cartRepository.save(cart);
    }

    public Optional<Cart> getCart(Long cartId) {
        return cartRepository.findById(cartId);
    }

    public Cart updateCart(Long cartId, Cart updatedCart) {
        return cartRepository.findById(cartId)
                .map(cart -> {
                    cart.getLineItems().clear();

                    if (updatedCart.getLineItems() != null) {
                        for (LineItem item : updatedCart.getLineItems()) {
                            cart.getLineItems().add(item);
                        }
                    }
                    else {
                        System.out.println("It entered Else");
                    }

                    return cartRepository.save(cart);
                })
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
//    public Cart updateCart(Long cartId, Cart updatedCart) {
//        return cartRepository.findById(cartId)
//                .map(cart -> {
//                    cart.setLineItems(updatedCart.getLineItems());
//                    return cartRepository.save(cart);
//                }).orElseThrow(() -> new RuntimeException("Cart not found"));
//    }

    public void emptyCart(long cartId) {
        Optional<Cart> existingCartOpt = cartRepository.findById(cartId);
        if (existingCartOpt.isPresent()) {
            Cart existingCart = existingCartOpt.get();
            existingCart.getLineItems().clear();  // Clear the list instead of replacing it
            cartRepository.save(existingCart);    // Hibernate will handle orphan removal
        } else {
            throw new RuntimeException("Cart not found with id " + cartId);
        }
    }

    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}