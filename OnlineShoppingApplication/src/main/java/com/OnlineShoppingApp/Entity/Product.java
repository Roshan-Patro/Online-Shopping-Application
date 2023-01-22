package com.OnlineShoppingApp.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@Size(min = 2, message = "first name must be of atleast 2 character length.")
	private String productName;
	
	@Min(value = 1, message = "price cannot be less than 1")
	private Double price;
	
	@Size(min = 2, message = "color must be of atleast 2 character length.")
	private String color;
	
	@Size(min = 2, message = "dimension must be of atleast 2 character length.")
	private String dimension;
	
	@Size(min = 5, message = "specification must be of atleast 5 character length.")
	private String specification;
	
	@Size(min = 3, message = "manufacturer must be of atleast 3 character length.")
	private String manufacturer;
	
	@JsonIgnore
	@ManyToOne // Bidirectional
	private Category category;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product") // Bidirectional
	private List<CartProduct> cartProductList;

}
