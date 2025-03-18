package com.capstone.cart_service.controller;

import com.capstone.cart_service.entity.Cart;
import com.capstone.cart_service.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.addCart(cart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCart(@PathVariable Long id) {
        return cartService.getCart(id)
                .map(cart -> ResponseEntity.ok((Object) cart))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart updatedCart) {
        Cart cart = cartService.updateCart(id, updatedCart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> emptyCart(@PathVariable long id) {
        cartService.emptyCart(id);
        return new ResponseEntity<String>("Line Items deleted", HttpStatus.OK);
    }
}