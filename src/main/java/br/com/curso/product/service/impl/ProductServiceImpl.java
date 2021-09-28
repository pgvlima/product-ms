package br.com.curso.product.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.curso.product.domain.Product;
import br.com.curso.product.repository.ProductRepository;
import br.com.curso.product.service.ProductService; 

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	@Override
	public List<Product> findByQuery(String q, Double minPrice, Double maxPrice) {
		return productRepository.findByQuery( q, minPrice, maxPrice);
	}
	
	@Override
	public Product findId(String id) throws NoSuchElementException {
		Optional<Product> product = productRepository.findById(id);	
		return product.get();

	}
	
	@Override
	public Product update(String id, Product product)  {
		Product result = findId(id);
		result.setName(product.getName());
		result.setDescription(product.getDescription());
		result.setPrice(product.getPrice());
		productRepository.save(result);
		return result;
	}

	@Override
	public void delete(String id)  {
		Product product = findId(id);
		productRepository.delete(product);
		
	}

	@Override
	public Product create(Product product) {
		return productRepository.save(product);		
	}


}
