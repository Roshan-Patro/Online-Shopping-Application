package com.OnlineShoppingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingApp.Entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer>{
	
	public Category findByCategoryName(String categoryName);
	
}
