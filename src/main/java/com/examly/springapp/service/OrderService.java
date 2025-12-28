package com.examly.springapp.service;

import com.examly.springapp.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
	Order create(Order order);
	List<Order> findAll();
	Optional<Order> findById(Long id);
	Order update(Long id, Order order);
	void delete(Long id);
	List<Order> findByCustomerId(Long customerId);
	List<Order> findByOrderStatus(Order.OrderStatus status);
}