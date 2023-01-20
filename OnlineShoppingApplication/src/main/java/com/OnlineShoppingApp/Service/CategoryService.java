package com.OnlineShoppingApp.Service;

import java.util.List;

import com.OnlineShoppingApp.Entity.Category;
import com.OnlineShoppingApp.Exception.CategoryException;

public interface CategoryService {
	
	public Category addCategory(Category category) throws CategoryException;
	
	public Category removeCategory(String ctegoryName) throws CategoryException;
	
	public List<Category> getAllCategory() throws CategoryException;
	
	
	
}
