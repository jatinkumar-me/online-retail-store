package com.aditya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya.model.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {

}
