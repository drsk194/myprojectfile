package com.examly.springapp.service;

import com.examly.springapp.model.UserAccount;
import com.examly.springapp.repository.UserAccountRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepo repo;
    public UserAccountServiceImpl(UserAccountRepo repo) { this.repo = repo; }

    @Override public UserAccount create(UserAccount userAccount) {
        UserAccount newUserAccount = new UserAccount();
        
        newUserAccount.setUsername(userAccount.getUsername());
        newUserAccount.setPasswordHash(userAccount.getPasswordHash());
        newUserAccount.setEmail(userAccount.getEmail());
        newUserAccount.setPhone(userAccount.getPhone());
        newUserAccount.setStatus(userAccount.getStatus());
        
        return repo.save(newUserAccount);
    }
    @Override public List<UserAccount> findAll() { return repo.findAll(); }
    @Override public Optional<UserAccount> findById(Long id) { return repo.findById(id); }
    @Override public UserAccount update(Long id, UserAccount userAccount) {
        UserAccount existing = repo.findById(id).orElseThrow();
        existing.setUsername(userAccount.getUsername());
        existing.setPasswordHash(userAccount.getPasswordHash());
        existing.setEmail(userAccount.getEmail());
        existing.setPhone(userAccount.getPhone());
        existing.setStatus(userAccount.getStatus());
        return repo.save(existing);
    }
    @Override public void delete(Long id) { repo.deleteById(id); }
}
