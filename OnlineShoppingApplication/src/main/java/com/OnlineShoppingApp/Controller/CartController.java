package com.OnlineShoppingApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShoppingApp.DTO.AddProductToCartDTO;
import com.OnlineShoppingApp.Entity.Cart;
import com.OnlineShoppingApp.Entity.CartProduct;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Service.CartProductService;
import com.OnlineShoppingApp.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cs;
	
	@PostMapping("/addProductToCart")
	public ResponseEntity<CartProduct> addProductToCart(@RequestBody AddProductToCartDTO dto, @RequestParam("key") String key){
		CartProduct cartP = cs.addProductToCart(dto, key);
		return new ResponseEntity<>(cartP, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/removeCartProduct/{cpId}")
	public ResponseEntity<CartProduct> removeProductFromCart(@PathVariable("cpId") Integer cartProductId, @RequestParam("key") String key){
		CartProduct cp = cs.removeProductFromCart(cartProductId, key);
		return new ResponseEntity<>(cp,HttpStatus.OK);
	}
	
	@DeleteMapping("/removeAllProductsInCart/{customerId}")
	public ResponseEntity<List<CartProduct>> removeAllProductsInCart(@PathVariable("customerId") Integer customerId, @RequestParam("key") String key){
		List<CartProduct> c = cs.removeAllProductsInCart(customerId, key);
		return new ResponseEntity<>(c,HttpStatus.OK);
	}
	
	@PutMapping("/updateQnt/{cpId}/{qnt}")
	public ResponseEntity<CartProduct> updateProductQuantityInCart(@PathVariable("cpId") Integer cartProductId, @PathVariable("qnt") Integer newQuantity, @RequestParam("key") String key){
		CartProduct cp = cs.updateProductQuantityInCart(cartProductId, newQuantity, key);
		return new ResponseEntity<CartProduct>(cp,HttpStatus.OK);
	}
	
	@GetMapping("/getAllProdsInCart/{customerId}")
	public ResponseEntity<List<CartProduct>> viewAllProductsInCart(@PathVariable("customerId") Integer customerId){
		List<CartProduct> cpList = cs.viewAllProductsInCart(customerId);
		return new ResponseEntity<List<CartProduct>>(cpList,HttpStatus.OK);
	}
	
	@GetMapping("/getTotCartPrcByCustomerId/{customerId}")
	public ResponseEntity<Double> findTotalCartPriceByCustomerId(@PathVariable("customerId") Integer customerId){
		Double totPrice = cs.findTotalCartPriceByCustomerId(customerId);
		return new ResponseEntity<Double>(totPrice,HttpStatus.OK);
	}
}
