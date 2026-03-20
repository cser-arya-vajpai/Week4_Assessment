package com.capgemini.controller;

import com.capgemini.model.Order;
import com.capgemini.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/{customerId}/orders")
    public Order create(@PathVariable Long customerId,
                        @RequestBody Order order) {
        return service.createOrder(customerId, order);
    }

    @PatchMapping("/{customerId}/orders/{orderId}/cancel")
    public Order cancel(@PathVariable Long orderId) {
        return service.cancelOrder(orderId);
    }
}