package com.aditya.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.model.LineItem;
import com.aditya.model.Order;
import com.aditya.repository.OrderRepository;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

//    public void placeOrder(Order order) {
//        // Save order logic
//        String orderDetails = "Order placed: " + order.getOrderId() + ", Products: " + order.getLineItems();
//        kafkaProducer.sendOrderDetails(orderDetails);
//    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

//    @Autowired
//    private KafkaProducer kafkaProducer;

    public Order updateOrder(Long orderId, Order updatedOrder) {
        return orderRepository.findById(orderId)
                .map(order -> {
                    // Clear existing line items
                    order.getLineItems().clear();

                    // Add updated line items
                    for (LineItem item : updatedOrder.getLineItems()) {
                        order.getLineItems().add(item);
                    }

                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

//    public Order updateOrder(Long orderId, Order updatedOrder) {
//        return orderRepository.findById(orderId)
//                .map(order -> {
//                    order.setLineItems(updatedOrder.getLineItems());
//                    return orderRepository.save(order);
//                }).orElseThrow(() -> new RuntimeException("Order not found"));
//    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}