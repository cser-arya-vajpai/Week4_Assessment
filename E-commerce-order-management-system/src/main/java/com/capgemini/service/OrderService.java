package com.capgemini.service;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Customer;
import com.capgemini.model.Order;
import com.capgemini.repository.CustomerRepository;
import com.capgemini.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerRepository customerRepo;

    public Order createOrder(Long customerId, Order order) {

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        BigDecimal total = order.getPricePerUnit()
                .multiply(BigDecimal.valueOf(order.getQuantity()));

        order.setTotalAmount(total);
        order.setCustomer(customer);

        return orderRepo.save(order);
    }

    public Order cancelOrder(Long orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus().equals("SHIPPED") ||
            order.getStatus().equals("DELIVERED")) {

            throw new RuntimeException("Cannot cancel this order");
        }

        order.setStatus("CANCELLED");

        return orderRepo.save(order);
    }
}