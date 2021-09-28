package br.com.curso.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.curso.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

	@Query("SELECT prod FROM Product prod WHERE (:q is null or lower(prod.name) like '%'||lower(:q)||'%' or lower(prod.description) like  '%'||lower(:q)||'%') and (:minPrice is null or prod.price>=:minPrice) and (:maxPrice is null or prod.price<=:maxPrice)")
	List<Product> findByQuery (String q, Double minPrice, Double maxPrice); 
}