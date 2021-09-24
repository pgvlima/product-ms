package br.com.curso.product.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.curso.product.domain.Product;
import br.com.curso.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductControler {

	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = service.findAll();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Product>> findById(@PathVariable String id) {
		Optional<Product> product = service.findById(id);
		if (product.isPresent()) {

			return ResponseEntity.ok(product);
		}

		return ResponseEntity.notFound().build();
 
	}

	@GetMapping("/search")
	public List<Product> search(@RequestParam(required = false) String q, @RequestParam(required = false) Double min_price, @RequestParam(required = false) Double max_price) {

		List<Product> product = service.findByQuery(q, min_price, max_price); 
		return product; 
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Product> insert(@RequestBody @Valid Product product, UriComponentsBuilder uriBuilder) {
		service.save(product);
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Product> update(@PathVariable String id, @RequestBody @Valid Product product,
			UriComponentsBuilder uriBuilder) {
		Optional<Product> optional = service.findById(id);
		if (optional.isPresent()) {
			Product prod = service.update(id, product);

			return ResponseEntity.ok().body(prod);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remove(@PathVariable String id) {
		Optional<Product> optional = service.findById(id);
		if (optional.isPresent()) {
			service.remove(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
