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

import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService pservice;
	
	@PostMapping("/")
	public ResponseEntity<Product> addProduct(@RequestBody Product product, @RequestParam String key){
		
		return new ResponseEntity<Product>(pservice.addProduct(product, key), HttpStatus.CREATED);
	
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> viewAllProducts(){
		
		return new ResponseEntity<List<Product>>(pservice.viewAllProducts(), HttpStatus.OK);
	
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @RequestParam String key){
		
		return new ResponseEntity<Product>(pservice.updateProduct(product, key), HttpStatus.OK);
	
	}
	
	
	@GetMapping("/viewProductById/{pid}")
	public ResponseEntity<Product> viewProductById(@PathVariable("pid") Integer productId){
		
		return new ResponseEntity<Product>(pservice.viewProductById(productId), HttpStatus.OK);
	
	}
	
	
	@GetMapping("/viewProductsByCategory/{cname}")
	public ResponseEntity<List<Product>> viewProductsByCategory(@PathVariable("cname") String cname){
		
		return new ResponseEntity<List<Product>>(pservice.viewProductsByCategory(cname), HttpStatus.OK);
	
	}
	
	
	@DeleteMapping("/deleteProductById/{pid}")
	public ResponseEntity<Product> removeProduct(@PathVariable("pid") Integer productId, @RequestParam String key){
		
		return new ResponseEntity<Product>(pservice.removeProduct(productId, key), HttpStatus.OK);
	
	}
	
	@PutMapping("/addProdToCat/{pid}/{cname}")
	public ResponseEntity<Product> addProductToACategory(@PathVariable("pid") Integer productID, @PathVariable("cname") String categoryName, @RequestParam String key){
		
		return new ResponseEntity<Product>(pservice.addCategoryToTheProduct(productID, categoryName, key), HttpStatus.OK);
	
	}
	
	
}
