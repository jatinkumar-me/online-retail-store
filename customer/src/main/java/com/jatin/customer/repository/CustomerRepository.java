package com.jatin.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatin.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
}
