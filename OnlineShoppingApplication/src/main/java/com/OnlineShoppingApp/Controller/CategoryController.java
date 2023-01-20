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
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShoppingApp.Entity.Category;
import com.OnlineShoppingApp.Service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	
	@Autowired
	private CategoryService cservice;
	
	@PostMapping("/")
	public ResponseEntity<Category> addCategoryHandler(@RequestBody Category category){
		
		return new ResponseEntity<Category>(cservice.addCategory(category), HttpStatus.CREATED);
	
	}
	
	
	@DeleteMapping("/{cname}")
	public ResponseEntity<Category> removeCategoryHandler(@PathVariable("cname") String categoryName){
		
		return new ResponseEntity<Category>(cservice.removeCategory(categoryName), HttpStatus.CREATED);
	
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategoryHandler(){
		
		return new ResponseEntity<List<Category>>(cservice.getAllCategory(), HttpStatus.CREATED);
	
	}
	

}
