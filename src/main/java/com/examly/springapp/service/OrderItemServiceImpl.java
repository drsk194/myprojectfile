package com.examly.springapp.service;

import com.examly.springapp.model.OrderItem;
import com.examly.springapp.model.Order;
import com.examly.springapp.model.Product;
import com.examly.springapp.repository.OrderItemRepo;
import com.examly.springapp.repository.OrderRepo;
import com.examly.springapp.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepo repo;
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    
    public OrderItemServiceImpl(OrderItemRepo repo, OrderRepo orderRepo, ProductRepo productRepo) { 
        this.repo = repo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @Override public OrderItem create(OrderItem orderItem) {
        OrderItem newOrderItem = new OrderItem();
        
        // resolve order
        Order order = orderRepo.findById(orderItem.getOrder().getId()).orElse(null);
        // resolve product
        Product product = productRepo.findById(orderItem.getProduct().getId()).orElse(null);
        
        newOrderItem.setOrder(order);
        newOrderItem.setProduct(product);
        newOrderItem.setQuantity(orderItem.getQuantity());
        
        return repo.save(newOrderItem);
    }
    @Override public List<OrderItem> findAll() { return repo.findAll(); }
    @Override public Optional<OrderItem> findById(Long id) { return repo.findById(id); }
    @Override public OrderItem update(Long id, OrderItem orderItem) {
        OrderItem existing = repo.findById(id).orElseThrow();
        
        // resolve order
        Order order = orderRepo.findById(orderItem.getOrder().getId()).orElse(null);
        // resolve product
        Product product = productRepo.findById(orderItem.getProduct().getId()).orElse(null);
        
        existing.setOrder(order);
        existing.setProduct(product);
        existing.setQuantity(orderItem.getQuantity());
        return repo.save(existing);
    }
    @Override public List<OrderItem> findByOrderId(Long orderId) { return repo.findByOrder_OrderId(orderId); }
    @Override public List<OrderItem> findByProductId(Long productId) { return repo.findByProduct_ProductId(productId); }
    @Override public void delete(Long id) { repo.deleteById(id); }
}