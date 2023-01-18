package com.OnlineShoppingApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Exception.ProductException;
import com.OnlineShoppingApp.Repository.ProductRepo;

public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo prepo;
	
	@Override
	public List<Product> viewAllProduct() throws ProductException {
		
		List<Product> allProduct = prepo.findAll();
		
		if(allProduct.size() == 0) {
			throw new ProductException("Product does not exists...");
		}
		else {
			return allProduct;
		}
		
	}

	@Override
	public Product addProduct(Product product) throws ProductException {
		
		Product existingProduct = prepo.findByProductName(product.getProductName());
		
		if(existingProduct == null) {
			
			return prepo.save(product);
			
		}
		else {
			
			if(existingProduct.getManufacturer().equals(product.getManufacturer())) {
				
				throw new ProductException("Product already exixts...");
				
			}
			else {
				
				return prepo.save(product);
				
			}
			
		}
		
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product viewProductById(Integer productID) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> viewProductByCategory(String categoryName) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product removeProduct(Integer productID) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

}
