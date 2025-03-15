package com.jatin.shopping_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatin.shopping_service.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

	public List<CustomerOrder> findByCustomerId(Long customerId);

	
}
