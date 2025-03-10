package com.wipro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.LineItem;
import com.wipro.entity.Order;
import com.wipro.repository.LineItemRepository;
import com.wipro.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LineItemRepository lineItemRepository;

    public Order addOrder(Order order) {
    	System.out.println(order);
        for (LineItem item : order.getLineItems()) {
            item.setOrder(order);
        }
        return orderRepository.save(order);
    }
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setLineItems(updatedOrder.getLineItems());
            return orderRepository.save(order);
        }
        throw new RuntimeException("Order not found");
    }

    public Order searchOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    
//    public List<Long> getOrderIdsByCustomerId(Long customerId) {
//        return orderRepository.findOrderIdsByCustomerId(customerId);
//    }
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public LineItem addLineItem(LineItem lineItem) {
        return lineItemRepository.save(lineItem);
    }

    public void deleteLineItem(Long itemId) {
        lineItemRepository.deleteById(itemId);
    }

    public LineItem updateLineItem(Long itemId, LineItem newLineItem) {
        Optional<LineItem> optionalLineItem = lineItemRepository.findById(itemId);
        if (optionalLineItem.isPresent()) {
            LineItem lineItem = optionalLineItem.get();
            lineItem.setProductName(newLineItem.getProductName());
            lineItem.setQuantity(newLineItem.getQuantity());
            lineItem.setPrice(newLineItem.getPrice());
            return lineItemRepository.save(lineItem);
        }
        throw new RuntimeException("Line Item not found");
    }
}