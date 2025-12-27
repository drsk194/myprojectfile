package com.examly.springapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Profile;
import com.examly.springapp.service.ProfileService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final ProfileService service;
    public ProfileController(ProfileService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Profile> create(@RequestBody Profile profile) {
        Profile saved = service.create(profile);
        Profile response = service.findById(saved.getId()).orElse(saved);
        return ResponseEntity.created(URI.create("/api/profiles/" + saved.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Profile profile) {
        try {
            return ResponseEntity.ok(service.update(id, profile));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<Profile>> getByName(@PathVariable String firstName) {
        return ResponseEntity.ok(service.findByName(firstName));
    }

    @GetMapping("/search/{firstName}/{address}")
    public ResponseEntity<List<Profile>> search(@PathVariable String firstName, @PathVariable String address) {
        return ResponseEntity.ok(service.searchByNameAndAddress(firstName, address));
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
