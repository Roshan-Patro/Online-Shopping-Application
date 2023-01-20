package com.OnlineShoppingApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineShoppingApp.Entity.Category;
import com.OnlineShoppingApp.Exception.CategoryException;
import com.OnlineShoppingApp.Repository.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	
	@Autowired
	private CategoryDao cdao;
	
	@Override
	public Category addCategory(Category category) throws CategoryException {
		// TODO Auto-generated method stub
		
		Category existingCategory = cdao.findByCategoryName(category.getCategoryName());
		
		if(existingCategory == null) {
			
			return cdao.save(category);
			
		}
		else {
			throw new CategoryException("Category already present in the list...");
		}
		
	}

	@Override
	public Category removeCategory(String ctegoryName) throws CategoryException {
		
		Category existingCategory = cdao.findByCategoryName(ctegoryName);
		
		if(existingCategory == null) {
			
			throw new CategoryException("Category not present in the list...");
			
		}
		else {
			
			cdao.delete(existingCategory);
			
			return existingCategory;
			
		}
		
	}

	@Override
	public List<Category> getAllCategory() throws CategoryException {
		
		List<Category> categoryList = cdao.findAll();
		
		if(categoryList.size() == 0) {
			throw new CategoryException("Category not exists...");
		}
		else {
			
			return categoryList;
			
		}
		
	}
	
	
	
}
