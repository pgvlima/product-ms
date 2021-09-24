package br.com.curso.product.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive; 



@Entity
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@NotNull(message = "Campo name não pode ser nulo")
	private String name;
	@NotNull (message = "Campo description não pode ser nulo")
	private String description;
	@Positive (message = "Campo price não pode ser negativo")
	private double price; 


	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}



}


