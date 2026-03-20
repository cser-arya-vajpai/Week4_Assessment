package com.capgemini.service;

import com.capgemini.model.Customer;
import com.capgemini.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    // CREATE
    public Customer createCustomer(Customer customer) {

        if (repo.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        return repo.save(customer);
    }

    // GET ACTIVE ONLY
    public List<Customer> getAllCustomers() {
        return repo.findByIsActiveTrue();
    }

    // GET ONE
    public Customer getCustomer(Long id) {
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (!customer.isActive()) {
            throw new RuntimeException("Customer is inactive");
        }

        return customer;
    }

    // UPDATE
    public Customer updateCustomer(Long id, Customer updated) {
        Customer existing = getCustomer(id);

        existing.setFullName(updated.getFullName());
        existing.setPhone(updated.getPhone());

        return repo.save(existing);
    }

    // SOFT DELETE
    public void deleteCustomer(Long id) {
        Customer customer = getCustomer(id);

        customer.setActive(false);  // 🔥 soft delete

        repo.save(customer);
    }

    // RESTORE
    public Customer restoreCustomer(Long id) {
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setActive(true);

        return repo.save(customer);
    }
}