package com.jatin.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatin.customer.entity.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

}
