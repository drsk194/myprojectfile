package com.examly.springapp.service;

import com.examly.springapp.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
	Product create(Product product);
	List<Product> findAll();
	Optional<Product> findById(Long id);
	Product update(Long id, Product product);
	void delete(Long id);
}