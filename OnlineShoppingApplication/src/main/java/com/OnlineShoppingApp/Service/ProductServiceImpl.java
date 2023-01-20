package com.OnlineShoppingApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineShoppingApp.Entity.Category;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Exception.CategoryException;
import com.OnlineShoppingApp.Exception.ProductException;
import com.OnlineShoppingApp.Repository.CategoryDao;
import com.OnlineShoppingApp.Repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao prepo;
	
	@Autowired
	private CategoryDao cdao;
	
	@Autowired
	private CategoryService cservice;
	
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
		
		Product existingProduct = prepo.findByProductName(product.getProductName());
		
		if(existingProduct == null) {
			throw new ProductException(product.getProductName()+" does not have in the product list");
		}
		else {
			
			Category category = new Category();
			
			category.setCategoryName(product.getCategory().getCategoryName());
			
			
			
			existingProduct.setColor(product.getColor());
			existingProduct.setDimension(product.getDimension());
			existingProduct.setManufacturer(product.getManufacturer());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setProductName(product.getProductName());
			existingProduct.setSpecification(product.getSpecification());
			
			existingProduct.setCategory(category);
			category.getProductList().add(existingProduct);
			
			return existingProduct;
			
			
		}
	}

	@Override
	public Product viewProductById(Integer productID) throws ProductException {
		
		Optional<Product> existingProduct = prepo.findById(productID);
		
		if(existingProduct.isPresent()) {
			return existingProduct.get();
		}
		else {
			throw new ProductException("Product not found with id : "+productID);
		}
		
	}

	@Override
	public List<Product> viewProductByCategory(String categoryName) throws ProductException, CategoryException {
		
		Category existingCategory = cdao.findByCategoryName(categoryName);
		
		if(existingCategory == null) {
			
			throw new CategoryException("Category Name : "+categoryName+" does not exists...");
			
		}
		else {
			
			List<Product> productList = prepo.findByCategory(existingCategory);
			
			if(productList.size() == 0) {
				
				throw new ProductException("Product does not exists with Category Name : "+categoryName);
				
			}
			else {
				
				return productList;
				
			}
			
		}
		
	}

	@Override
	public Product removeProduct(Integer productID) throws ProductException {
		
		Optional<Product> existingProduct = prepo.findById(productID);
		
		if(existingProduct.isEmpty()) {
			throw new ProductException("Product not found...");
		}
		else {
			
			Product product = existingProduct.get();
			
			prepo.delete(product);
			
			return existingProduct.get();
		}
		
	}

	@Override
	public Product addCategoryToTheProduct(Integer productId, String cname) throws ProductException, CategoryException {
		
		Category category = cdao.findByCategoryName(cname);
		
		if(category == null) {
			throw new CategoryException("Category does not exists please add this category...");
		}
		else {
			
			Optional<Product> product = prepo.findById(productId);
			
			if(product.isPresent()) {
				
				Product existingProduct = product.get();
				
				existingProduct.setCategory(category);
				
				return prepo.save(existingProduct);
				
			}
			else {
				throw new ProductException("Product does not exists...");
			}
			
		}
		
	}
	
}
