package com.examly.springapp.service;

import com.examly.springapp.model.Product;
import com.examly.springapp.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepo repo;
	
	public ProductServiceImpl(ProductRepo repo) { 
		this.repo = repo;
	}

	@Override public Product create(Product product) {
		Product newProduct = new Product();
		
		newProduct.setProductName(product.getProductName());
		newProduct.setPrice(product.getPrice());
		newProduct.setStockQuantity(product.getStockQuantity());
		
		return repo.save(newProduct);
	}
	@Override public List<Product> findAll() { return repo.findAll(); }
	@Override public Optional<Product> findById(Long id) { return repo.findById(id); }
	@Override public Product update(Long id, Product product) {
		Product existing = repo.findById(id).orElseThrow();
		
		existing.setProductName(product.getProductName());
		existing.setPrice(product.getPrice());
		existing.setStockQuantity(product.getStockQuantity());
		return repo.save(existing);
	}
	@Override public void delete(Long id) { repo.deleteById(id); }
}