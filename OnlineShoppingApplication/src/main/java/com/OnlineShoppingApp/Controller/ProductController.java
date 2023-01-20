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
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService pservice;
	
	@PostMapping("/")
	public ResponseEntity<Product> addProductHandler(@RequestBody Product product){
		
		return new ResponseEntity<Product>(pservice.addProduct(product), HttpStatus.CREATED);
	
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> viewAllProductHandler(){
		
		return new ResponseEntity<List<Product>>(pservice.viewAllProduct(), HttpStatus.OK);
	
	}
	
	@PutMapping("/")
	public ResponseEntity<Product> updateProductHandler(@RequestBody Product product){
		
		return new ResponseEntity<Product>(pservice.updateProduct(product), HttpStatus.CREATED);
	
	}
	
	
	@GetMapping("/{pid}")
	public ResponseEntity<Product> viewProductByIdHandler(@PathVariable("pid") Integer productId){
		
		return new ResponseEntity<Product>(pservice.viewProductById(productId), HttpStatus.OK);
	
	}
	
	
	@GetMapping("/{cname}")
	public ResponseEntity<List<Product>> viewProductByCategoryHandler(@PathVariable("cname") String cname){
		
		return new ResponseEntity<List<Product>>(pservice.viewProductByCategory(cname), HttpStatus.OK);
	
	}
	
	
	@DeleteMapping("/{pid}")
	public ResponseEntity<Product> removeProductHandler(@PathVariable("pid") Integer productId){
		
		return new ResponseEntity<Product>(pservice.removeProduct(productId), HttpStatus.OK);
	
	}
	
	@PutMapping("/{pid}/{cname}")
	public ResponseEntity<Product> updateProductHandler(@PathVariable("pid") Integer productID, @PathVariable("cname") String categoryName){
		
		return new ResponseEntity<Product>(pservice.addCategoryToTheProduct(productID, categoryName), HttpStatus.CREATED);
	
	}
	
	
}
