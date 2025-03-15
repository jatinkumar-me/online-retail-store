package com.jatin.shopping_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatin.shopping_service.entity.CustomerCart;

public interface CustomerCartRepository extends JpaRepository<CustomerCart, Long> {

	public Optional<CustomerCart> findByCustomerId(Long customerId);
}
