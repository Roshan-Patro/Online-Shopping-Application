package com.OnlineShoppingApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShoppingApp.Entity.Category;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Repository.SessionDao;
import com.OnlineShoppingApp.Service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	
	@Autowired
	private CategoryService cservice;
	
	
	
	@PostMapping("/addCat")
	public ResponseEntity<Category> addCategory(@RequestBody Category category, @RequestParam String key){
		
		return new ResponseEntity<Category>(cservice.addCategory(category, key), HttpStatus.CREATED);
	
	}
	
	
	@DeleteMapping("/removeCat/{cname}")
	public ResponseEntity<Category> removeCategory(@PathVariable("cname") String categoryName, @RequestParam String key){
		
		return new ResponseEntity<Category>(cservice.removeCategory(categoryName,key), HttpStatus.OK);
	
	}
	
	@GetMapping("/getAllCat")
	public ResponseEntity<List<Category>> getAllCategory(){
		
		return new ResponseEntity<List<Category>>(cservice.getAllCategory(), HttpStatus.ACCEPTED);
	
	}
	
	@GetMapping("/getCatById/{catId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("catId") Integer catId){
		
		return new ResponseEntity<Category>(cservice.getCategoryById(catId), HttpStatus.ACCEPTED);
	
	}
	

}
