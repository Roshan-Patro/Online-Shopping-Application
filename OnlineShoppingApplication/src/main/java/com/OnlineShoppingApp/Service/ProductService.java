package com.OnlineShoppingApp.Service;

import java.util.List;

import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Exception.CategoryException;
import com.OnlineShoppingApp.Exception.ProductException;

public interface ProductService {
	
	public List<Product> viewAllProducts() throws ProductException;
	
	public Product addProduct(Product product, String key) throws ProductException;
	
	public Product updateProduct(Product product, String key) throws ProductException;
	
	public Product viewProductById(Integer productID) throws ProductException;
	
	public List<Product> viewProductsByCategory(String categoryName) throws ProductException, CategoryException;
	
	public Product removeProduct(Integer productID, String key) throws ProductException;
	
	public Product addCategoryToTheProduct(Integer productId, String cname, String key) throws ProductException, CategoryException;
	
}
