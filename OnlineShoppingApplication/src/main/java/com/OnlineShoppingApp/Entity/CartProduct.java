package com.OnlineShoppingApp.Entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.OnlineShoppingApp.Enum.CartProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartProductId;

	private Integer quantity;
	
	@Enumerated(EnumType.STRING)
	private CartProductStatus cpStatus;
	
	@JsonIgnore
	@ManyToOne // Bidirectional
	private Product product;
	
	@JsonIgnore
	@ManyToOne // Bidirectional
	private Cart cart;
}
