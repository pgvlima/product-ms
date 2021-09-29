package br.com.curso.product.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import br.com.curso.product.domain.dto.ProductDto;
import br.com.curso.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<ProductDto> findAll() {
		return productService.findAll().stream().map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());			
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable String id) {
		Product product = productService.findId(id);
			return ResponseEntity.ok(modelMapper.map(product,ProductDto.class));  
	}

	@GetMapping("/search")
	public List<ProductDto> findByQuery(@RequestParam(required = false) String q, @RequestParam(required = false) Double min_price, @RequestParam(required = false) Double max_price) {
		return productService.findByQuery(q, min_price, max_price).stream().map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList()); 
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductDto productDto, UriComponentsBuilder uriBuilder) {
		Product product = productService.create(modelMapper.map(productDto,Product.class)); 
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(modelMapper.map(product,ProductDto.class).getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(product,ProductDto.class));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> update(@PathVariable String id, @RequestBody @Valid ProductDto productDto,
			UriComponentsBuilder uriBuilder)  {
		Product product = productService.update(id,modelMapper.map(productDto,Product.class)); 
		return ResponseEntity.ok().body(modelMapper.map(product,ProductDto.class));
				
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) {
	productService.delete(id);
	return ResponseEntity.ok().build();
	}
}
