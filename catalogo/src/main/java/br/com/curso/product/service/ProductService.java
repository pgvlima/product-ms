package br.com.curso.product.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.product.domain.Product;
import br.com.curso.product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product update(String id, Product product) {
		Product obj = productRepository.getById(id);
		obj.setName(product.getName());
		obj.setDescription(product.getDescription());
		obj.setPrice(product.getPrice());
		productRepository.save(obj);
		return obj;
	}

	public void remove(String id) {
		Product product = productRepository.getById(id);
		productRepository.delete(product);
		
	}

	public void save(@Valid Product product) {
		productRepository.save(product);		
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Optional<Product> findById(String id) {
		return productRepository.findById(id);
	}

	public List<Product> findByQuery(String q, Double min_price, Double max_price) {
		return productRepository.findByQuery( q, min_price, max_price);
	}
	


}
