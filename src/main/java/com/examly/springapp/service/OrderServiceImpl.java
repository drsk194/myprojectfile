package com.examly.springapp.service;

import com.examly.springapp.model.Order;
import com.examly.springapp.model.Customer;
import com.examly.springapp.repository.OrderRepo;
import com.examly.springapp.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
	private final OrderRepo repo;
	private final CustomerRepo customerRepo;
	
	public OrderServiceImpl(OrderRepo repo, CustomerRepo customerRepo) { 
		this.repo = repo;
		this.customerRepo = customerRepo;
	}

	@Override public Order create(Order order) {
		Order newOrder = new Order();
		
		// resolve customer
		Customer customer = customerRepo.findById(order.getCustomer().getId()).orElse(null);
		
		newOrder.setCustomer(customer);
		newOrder.setOrderDate(order.getOrderDate());
		newOrder.setOrderStatus(order.getOrderStatus());
		
		return repo.save(newOrder);
	}
	@Override public List<Order> findAll() { return repo.findAll(); }
	@Override public Optional<Order> findById(Long id) { return repo.findById(id); }
	@Override public Order update(Long id, Order order) {
		Order existing = repo.findById(id).orElseThrow();
		
		// resolve customer
		Customer customer = customerRepo.findById(order.getCustomer().getId()).orElse(null);
		
		existing.setCustomer(customer);
		existing.setOrderDate(order.getOrderDate());
		existing.setOrderStatus(order.getOrderStatus());
		return repo.save(existing);
	}
	@Override public void delete(Long id) { repo.deleteById(id); }
	@Override public List<Order> findByCustomerId(Long customerId) { return repo.findByCustomerId(customerId); }
	@Override public List<Order> findByOrderStatus(Order.OrderStatus status) { return repo.findByOrderStatus(status); }
}