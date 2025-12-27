package com.examly.springapp.controller;

import com.examly.springapp.model.UserRoleMapping;
import com.examly.springapp.service.UserRoleMappingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/userRoleMappings")
public class UserRoleMappingController {
    private final UserRoleMappingService service;
    public UserRoleMappingController(UserRoleMappingService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<UserRoleMapping> create(@RequestBody UserRoleMapping mapping) {
        UserRoleMapping saved = service.create(mapping);
        UserRoleMapping response = service.findById(saved.getId()).orElse(saved);
        return ResponseEntity.created(URI.create("/api/userRoleMappings/" + saved.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserRoleMapping>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserRoleMapping mapping) {
        try {
            return ResponseEntity.ok(service.update(id, mapping));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Long userId) {
        List<UserRoleMapping> list = service.findByUserId(userId);
        if (list.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<List<UserRoleMapping>> getByUserIdAndRoleId(@PathVariable Long userId, @PathVariable Long roleId) {
        return ResponseEntity.ok(service.findByUserIdAndRoleId(userId, roleId));
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


