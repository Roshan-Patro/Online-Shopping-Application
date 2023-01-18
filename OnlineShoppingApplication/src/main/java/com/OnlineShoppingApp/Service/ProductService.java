package com.OnlineShoppingApp.Service;

import java.util.List;

import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Exception.ProductException;

public interface ProductService {
	
	public List<Product> viewAllProduct() throws ProductException;
	
	public Product addProduct(Product product) throws ProductException;
	
	public Product updateProduct(Product product) throws ProductException;
	
	public Product viewProductById(Integer productID) throws ProductException;
	
	public List<Product> viewProductByCategory(String categoryName) throws ProductException;
	
	public Product removeProduct(Integer productID) throws ProductException;
	
}
