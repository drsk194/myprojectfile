package com.examly.springapp.controller;

import com.examly.springapp.model.UserAccount;
import com.examly.springapp.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/userAccounts")
public class UserAccountController {
    private final UserAccountService service;
    public UserAccountController(UserAccountService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<UserAccount> create(@RequestBody UserAccount user) {
        UserAccount saved = service.create(user);
        UserAccount response = service.findById(saved.getId()).orElse(saved);
        URI location = URI.create("/api/userAccounts/" + saved.getId());
        return ResponseEntity.created(location).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<UserAccount>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserAccount user) {
        try {
            return ResponseEntity.ok(service.update(id, user));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
