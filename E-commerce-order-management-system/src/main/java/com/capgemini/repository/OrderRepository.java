package com.capgemini.repository;

import com.capgemini.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByCustomerCustomerId(Long customerId, Pageable pageable);

    Page<Order> findByCustomerCustomerIdAndStatus(Long customerId, String status, Pageable pageable);
}