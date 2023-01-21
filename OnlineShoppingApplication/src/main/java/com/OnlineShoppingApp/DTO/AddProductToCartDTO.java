package com.OnlineShoppingApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddProductToCartDTO {
	
	private Integer cartId; 
	
	private Integer productId;
	
	private Integer quantity;
	
}
