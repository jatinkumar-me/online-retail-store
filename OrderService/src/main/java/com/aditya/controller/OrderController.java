package com.aditya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.model.LineItem;
import com.aditya.model.Order;
import com.aditya.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
    	System.out.println("Mic 1");
        Order savedOrder = orderService.addOrder(order);
        System.out.println("Mic 2");
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> searchOrder(@PathVariable Long id) {
        Order order = orderService.searchOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<List<Long>> getOrderIdsByCustomerId(@PathVariable Long customerId) {
//        List<Long> orderIds = orderService.getOrderIdsByCustomerId(customerId);
//        return ResponseEntity.ok(orderIds);
//    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/lineitem/")
    public ResponseEntity<LineItem> addLineItem(@RequestBody LineItem lineItem) {
        LineItem savedLineItem = orderService.addLineItem(lineItem);
        return new ResponseEntity<>(savedLineItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/lineitem/{id}")
    public ResponseEntity<String> deleteLineItem(@PathVariable Long id) {
        orderService.deleteLineItem(id);
        return new ResponseEntity<>("Line Items deleted",HttpStatus.OK);
    }

    @PutMapping("/lineitem/{id}")
    public ResponseEntity<LineItem> updateLineItem(@PathVariable Long id, @RequestBody LineItem lineItem) {
        LineItem updatedLineItem = orderService.updateLineItem(id, lineItem);
        return new ResponseEntity<>(updatedLineItem, HttpStatus.OK);
    }
}

