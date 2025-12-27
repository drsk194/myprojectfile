package com.examly.springapp.controller;

import com.examly.springapp.model.OrderItem;
import com.examly.springapp.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {
    private final OrderItemService service;
    public OrderItemController(OrderItemService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItem orderItem) {
        OrderItem saved = service.create(orderItem);
        return ResponseEntity.created(URI.create("/api/orderItems/" + saved.getId())).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        try {
            return ResponseEntity.ok(service.update(id, orderItem));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(service.findByOrderId(orderId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderItem>> getByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(service.findByProductId(productId));
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