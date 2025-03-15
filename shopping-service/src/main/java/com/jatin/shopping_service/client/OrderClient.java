package com.jatin.shopping_service.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.jatin.shopping_service.entity.Order;

@FeignClient(name = "order-service")
public interface OrderClient {

    Order createOrder(Order order);

    Order getOrder(Long orderId);
}
