package com.OnlineShoppingApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineShoppingApp.Entity.Category;
import com.OnlineShoppingApp.Entity.CurrentSession;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Exception.CategoryException;
import com.OnlineShoppingApp.Exception.ProductException;
import com.OnlineShoppingApp.Repository.CategoryDao;
import com.OnlineShoppingApp.Repository.SessionDao;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	
	@Autowired
	private CategoryDao cdao;
	
	@Autowired
	private SessionDao sdao; 
	
	@Override
	public Category addCategory(Category category, String key) throws CategoryException {
		CurrentSession validSession = sdao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("ADMIN")) {
			Category existingCategory = cdao.findByCategoryName(category.getCategoryName());
			
			if(existingCategory == null) {
				
				return cdao.save(category);
				
			}
			else {
				throw new CategoryException("Category already present in the list...");
			}
		}
		
		throw new ProductException("Either invalid key or not of an Admin.");
	}

	@Override
	public Category removeCategory(String ctegoryName, String key) throws CategoryException {
		CurrentSession validSession = sdao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("ADMIN")) {
			Category existingCategory = cdao.findByCategoryName(ctegoryName);
			
			if(existingCategory == null) {
				
				throw new CategoryException("Category not present in the list...");
				
			}
			else {
				
				cdao.delete(existingCategory);
				
				return existingCategory;
				
			}
		}
		throw new ProductException("Either invalid key or not of an Admin.");
		
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

	@Override
	public Category getCategoryById(Integer catId) throws CategoryException {
		Optional<Category> catOpt = cdao.findById(catId);
		if(catOpt.isPresent()) {
			return catOpt.get();
		}
		throw new CategoryException("No category found with id: "+catId);
	}


	
	
	
}
