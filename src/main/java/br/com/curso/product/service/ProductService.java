package br.com.curso.product.service;

import java.util.List;

import br.com.curso.product.domain.Product;


public interface ProductService {
	
	List<Product> findAll();
	
	Product findId(String id);
	
	List<Product> findByQuery(String q, Double minPrice, Double maxPrice);
	
	Product create(Product product);
	
	void delete(String id);
	
	Product update(String id, Product product);
	
	
	

}
