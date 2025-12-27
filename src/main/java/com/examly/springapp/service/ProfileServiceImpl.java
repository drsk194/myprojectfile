package com.examly.springapp.service;

import com.examly.springapp.model.Profile;
import com.examly.springapp.model.Customer;
import com.examly.springapp.repository.ProfileRepo;
import com.examly.springapp.repository.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepo repo;
    private final CustomerRepo customerRepo;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public ProfileServiceImpl(ProfileRepo repo, CustomerRepo customerRepo) { 
        this.repo = repo; 
        this.customerRepo = customerRepo;
    }

    @Override public Profile create(Profile profile) {
        if (profile.getCustomer() != null && profile.getCustomer().getId() != null) {
            Customer customer = customerRepo.findById(profile.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
            profile.setCustomer(customer);
        }
        else if (profile.getFirstName() != null || profile.getLastName() != null) {
            Customer customer = new Customer();
            customer.setFirstName(profile.getFirstName());
            customer.setLastName(profile.getLastName());
            customer.setStatus(Customer.Status.ACTIVE);
            customer = customerRepo.save(customer);
            profile.setCustomer(customer);
        }
        return repo.save(profile);
    }
    @Override public List<Profile> findAll() { return repo.findAll(); }
    @Override public Optional<Profile> findById(Long id) { return repo.findById(id); }
    @Override public Profile update(Long id, Profile profile) {
        Profile existing = repo.findById(id).orElseThrow();
        existing.setAddress(profile.getAddress());
        existing.setDob(profile.getDob());
        existing.setGender(profile.getGender());
        if (profile.getFirstName() != null) {
            existing.setFirstName(profile.getFirstName());
        }
        if (profile.getLastName() != null) {
            existing.setLastName(profile.getLastName());
        }
        if (profile.getCustomer() != null) {
            existing.setCustomer(profile.getCustomer());
        }
        return repo.save(existing);
    }
    @Override public List<Profile> findByName(String firstName) { return repo.findByCustomer_FirstName(firstName); }
    @Override public List<Profile> searchByNameAndAddress(String firstName, String address) {
        return repo.findByCustomer_FirstNameAndAddress(firstName, address);
    }
    @Override public void delete(Long id) { repo.deleteById(id); }
}