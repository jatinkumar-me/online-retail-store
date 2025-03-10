package com.wipro.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wipro.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//	
//	 @Query("SELECT o.orderId FROM Order o WHERE o.customerId = :customerId")
//	 List<Long> findOrderIdsByCustomerId(Long customerId);
	 List<Order> findByCustomerId(Long customerId);
}