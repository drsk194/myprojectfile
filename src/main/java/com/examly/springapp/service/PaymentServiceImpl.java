package com.examly.springapp.service;

import com.examly.springapp.model.Payment;
import com.examly.springapp.model.Order;
import com.examly.springapp.repository.PaymentRepo;
import com.examly.springapp.repository.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
	private final PaymentRepo repo;
	private final OrderRepo orderRepo;
	
	public PaymentServiceImpl(PaymentRepo repo, OrderRepo orderRepo) { 
		this.repo = repo;
		this.orderRepo = orderRepo;
	}

	@Override public Payment create(Payment payment) {
		Payment newPayment = new Payment();
		
		// resolve order
		Order order = orderRepo.findById(payment.getOrder().getId()).orElse(null);
		
		newPayment.setOrder(order);
		newPayment.setPaymentAmount(payment.getPaymentAmount());
		newPayment.setPaymentMethod(payment.getPaymentMethod());
		
		return repo.save(newPayment);
	}
	@Override public List<Payment> findAll() { return repo.findAll(); }
	@Override public Optional<Payment> findById(Long id) { return repo.findById(id); }
	@Override public Payment update(Long id, Payment payment) {
		Payment existing = repo.findById(id).orElseThrow();
		
		// resolve order
		Order order = orderRepo.findById(payment.getOrder().getId()).orElse(null);
		
		existing.setOrder(order);
		existing.setPaymentAmount(payment.getPaymentAmount());
		existing.setPaymentMethod(payment.getPaymentMethod());
		return repo.save(existing);
	}
	@Override public void delete(Long id) { repo.deleteById(id); }
}