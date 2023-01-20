package com.OnlineShoppingApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShoppingApp.Entity.Cart;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Service.CartProductService;

@RestController
public class CartController {
	@Autowired
	private CartProductService cs;
	
	
	public ResponseEntity<Cart> addProductToCartHandler(Cart cart, Product prod, Integer quantity ){
		Cart c=cs.addProductToCart(cart, prod, quantity);
		return new ResponseEntity<>(c,HttpStatus.OK);
	}
	
	public ResponseEntity<Cart> removeProductHandler(Cart cart, Product prod){
		Cart c=cs.removeProductFromCart(cart, prod);
		return new ResponseEntity<>(c,HttpStatus.OK);
	}
	
	public ResponseEntity<Cart> removeAllProductHandler(Cart cart){
		Cart c=cs.removeAllProduct(cart);
		return new ResponseEntity<>(c,HttpStatus.OK);
	}
	
	public ResponseEntity<Cart> updateProductQuantity(Cart cart, Product product, Integer quantity){
		Cart c=cs.updateProductQuantity(cart, product, quantity);
		return new ResponseEntity<Cart>(c,HttpStatus.OK);
	}
	
	
}
