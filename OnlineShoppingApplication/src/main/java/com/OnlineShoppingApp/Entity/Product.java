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
	
	private String productName;
	private Double price;
	private String color;
	private String dimension;
	private String specification;
	private String manufacturer;
	
	@JsonIgnore
	@ManyToOne // Bidirectional
	private Category category;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product") // Bidirectional
	private List<CartProduct> cartProductList;

}
