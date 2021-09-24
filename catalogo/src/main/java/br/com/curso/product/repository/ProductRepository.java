package br.com.curso.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.curso.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

	@Query("SELECT prod FROM Product prod WHERE (:q is null or prod.name like %:q% or prod.description like %:q%) and (:min_price is null or prod.price>=:min_price) and (:max_price is null or prod.price<=:max_price)")
	List<Product> findByQuery (String q, Double min_price, Double max_price);
}