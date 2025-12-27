package com.examly.springapp.service;

import com.examly.springapp.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer create(Customer customer);
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    Customer update(Long id, Customer customer);
    void delete(Long id);
}