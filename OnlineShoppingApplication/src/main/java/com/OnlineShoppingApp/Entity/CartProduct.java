package com.OnlineShoppingApp.Entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

   	// @NotNull(message = "slot count cannot be null.")
	@Min(value = 1, message = "quantity cannot be less than 1")
	private Integer quantity;
	
	// @NotNull
	@Enumerated(EnumType.STRING)
	private CartProductStatus cpStatus;
	
	@JsonIgnore
	@ManyToOne // Bidirectional
	private Product product;
	
	@JsonIgnore
	@ManyToOne // Bidirectional
	private Cart cart;
}
