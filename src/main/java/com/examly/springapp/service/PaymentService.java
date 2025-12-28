package com.examly.springapp.service;

import com.examly.springapp.model.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
	Payment create(Payment payment);
	List<Payment> findAll();
	Optional<Payment> findById(Long id);
	Payment update(Long id, Payment payment);
	void delete(Long id);
}