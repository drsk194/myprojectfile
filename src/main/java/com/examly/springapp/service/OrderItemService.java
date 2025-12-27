package com.examly.springapp.service;

import com.examly.springapp.model.OrderItem;
import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItem create(OrderItem orderItem);
    List<OrderItem> findAll();
    Optional<OrderItem> findById(Long id);
    OrderItem update(Long id, OrderItem orderItem);
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByProductId(Long productId);
    void delete(Long id);
}