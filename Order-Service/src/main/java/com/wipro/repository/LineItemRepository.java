package com.wipro.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {
	
}