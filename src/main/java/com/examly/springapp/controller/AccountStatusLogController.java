package com.examly.springapp.controller;

import java.util.List;
import java.net.URI;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.AccountStatusLog;
import com.examly.springapp.service.AccountStatusLogService;

@RestController
@RequestMapping("/api/logs")
public class AccountStatusLogController {

    private final AccountStatusLogService service;

    public AccountStatusLogController(AccountStatusLogService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<AccountStatusLog> addAccountStatusLog(@RequestBody AccountStatusLog accountStatusLog){
        AccountStatusLog saved = service.create(accountStatusLog);
        return ResponseEntity.created(URI.create("/api/logs/" + saved.getId())).body(saved);
    }
    @GetMapping
    public ResponseEntity<List<AccountStatusLog>> getAllAccountStatusLog(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountStatusLog(@PathVariable int id){
        return service.findById((long) id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> putAccountStatusLog(@PathVariable int id,@RequestBody AccountStatusLog accountStatusLog){
        long lid = id;
        if(service.findById(lid).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(lid, accountStatusLog));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountStatusLog(@PathVariable int id){
        long lid = id;
        if(service.findById(lid).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.delete(lid);
        return ResponseEntity.noContent().build();
    }
}
