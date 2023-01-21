package com.OnlineShoppingApp.Service;

import java.util.List;

import com.OnlineShoppingApp.Entity.Category;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Exception.CategoryException;

public interface CategoryService {
	
	public Category addCategory(Category category, String key) throws CategoryException;
	
	public Category removeCategory(String ctegoryName, String key) throws CategoryException;
	
	public List<Category> getAllCategory() throws CategoryException;
	
	public Category getCategoryById(Integer catId) throws CategoryException;
	 
	
}
