package com.OnlineShoppingApp.DTO;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddProductToCartDTO {
	
	@Min(value = 1, message = "cartId cannot be less than 1")
	private Integer cartId; 
	
	@Min(value = 1, message = "productId cannot be less than 1")
	private Integer productId;
	
	@Min(value = 1, message = "quantity cannot be less than 1")
	private Integer quantity;
	
}
