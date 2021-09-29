package br.com.curso.product.domain.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	

	private String id;
	@NotBlank(message = "Campo name não pode ser nulo")
	private String name;
	@NotBlank(message = "Campo description não pode ser nulo")
	private String description;
	@Positive(message = "Campo price não pode ser negativo")
	@NotNull(message = "Campo price não pode ser nulo")
	private Double price;

	
}
