package com.examly.springapp.service;

import com.examly.springapp.model.OrderItem;
import com.examly.springapp.repository.OrderItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepo repo;
    public OrderItemServiceImpl(OrderItemRepo repo) { this.repo = repo; }

    @Override public OrderItem create(OrderItem orderItem) { return repo.save(orderItem); }
    @Override public List<OrderItem> findAll() { return repo.findAll(); }
    @Override public Optional<OrderItem> findById(Long id) { return repo.findById(id); }
    @Override public OrderItem update(Long id, OrderItem orderItem) {
        OrderItem existing = repo.findById(id).orElseThrow();
        existing.setOrder(orderItem.getOrder());
        existing.setProduct(orderItem.getProduct());
        existing.setQuantity(orderItem.getQuantity());
        return repo.save(existing);
    }
    @Override public List<OrderItem> findByOrderId(Long orderId) { return repo.findByOrder_OrderId(orderId); }
    @Override public List<OrderItem> findByProductId(Long productId) { return repo.findByProduct_ProductId(productId); }
    @Override public void delete(Long id) { repo.deleteById(id); }
}