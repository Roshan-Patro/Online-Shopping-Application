package com.OnlineShoppingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingApp.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	public Product findByProductName(String productName);
	
}
