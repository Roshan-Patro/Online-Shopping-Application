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
import com.OnlineShoppingApp.Repository.ProductDao;
import com.OnlineShoppingApp.Repository.SessionDao;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao prepo;
	
	@Autowired
	private CategoryDao cdao;
	
	@Autowired
	private SessionDao sdao; 
	
	@Override
	public List<Product> viewAllProducts() throws ProductException {
		
		List<Product> allProducts = prepo.findAll();
		
		if(allProducts.size() == 0) {
			throw new ProductException("Product does not exists...");
		}
		else {
			return allProducts;
		}
		
	}

	@Override
	public Product addProduct(Product product, String key) throws ProductException {
		
		CurrentSession validSession = sdao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("ADMIN")) {
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
		throw new ProductException("Either invalid key or not of an Admin.");
		
	}

	@Override
	public Product updateProduct(Product product, String key) throws ProductException {
		CurrentSession validSession = sdao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("ADMIN")) {
			Optional<Product> existingProductopt = prepo.findById(product.getProductId());
			
			if(existingProductopt.isPresent()==false) {
				throw new ProductException(product.getProductName()+" does not have in the product list");
			}
			else {
				Product existingProduct = existingProductopt.get();
				existingProduct.setColor(product.getColor());
				existingProduct.setManufacturer(product.getManufacturer());
				existingProduct.setDimension(product.getDimension());
				existingProduct.setPrice(product.getPrice());
				existingProduct.setProductName(product.getProductName());
				existingProduct.setSpecification(product.getSpecification());
				
				Product updatedProduct = prepo.save(existingProduct);
				
				return updatedProduct;
			}
		}
		throw new ProductException("Either invalid key or not of an Admin.");
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
	public List<Product> viewProductsByCategory(String categoryName) throws ProductException, CategoryException {
		
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
	public Product removeProduct(Integer productID, String key) throws ProductException {
		CurrentSession validSession = sdao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("ADMIN")) {
			Optional<Product> existingProduct = prepo.findById(productID);
			
			if(existingProduct.isEmpty()) {
				throw new ProductException("Product not found...");
			}
			else {
				
				Product product = existingProduct.get();
				
				prepo.delete(product);
				
				return product;
			}
		}
		throw new ProductException("Either invalid key or not of an Admin.");
		
	}

	@Override
	public Product addCategoryToTheProduct(Integer productId, String cname, String key) throws ProductException, CategoryException {
		
		CurrentSession validSession = sdao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("ADMIN")) {
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
		
		throw new ProductException("Either invalid key or not of an Admin.");
	}
	
}
