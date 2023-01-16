package com.OnlineShoppingApp.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productid;
	private String productName;
	private Double price;
	private String color;
	private String dimension;
	private String specification;
	private String manufacturer;
	
	@ManyToOne // Bidirectional
	private Category category;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product") // Bidirectional
	private List<CartProduct> cartProductList;
}
