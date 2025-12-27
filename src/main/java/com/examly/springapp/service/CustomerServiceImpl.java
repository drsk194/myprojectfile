package com.examly.springapp.service;

import com.examly.springapp.model.Customer;
import com.examly.springapp.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo repo;
    public CustomerServiceImpl(CustomerRepo repo) { this.repo = repo; }

    @Override public Customer create(Customer customer) { return repo.save(customer); }
    @Override public List<Customer> findAll() { return repo.findAll(); }
    @Override public Optional<Customer> findById(Long id) { return repo.findById(id); }
    @Override public Customer update(Long id, Customer customer) {
        Customer existing = repo.findById(id).orElseThrow();
        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        existing.setStatus(customer.getStatus());
        return repo.save(existing);
    }
    @Override public void delete(Long id) { repo.deleteById(id); }
}