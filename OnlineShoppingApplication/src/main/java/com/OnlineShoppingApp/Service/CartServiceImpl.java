package com.OnlineShoppingApp.Service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineShoppingApp.DTO.AddProductToCartDTO;
import com.OnlineShoppingApp.Entity.Cart;
import com.OnlineShoppingApp.Entity.CartProduct;
import com.OnlineShoppingApp.Entity.CurrentSession;
import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Exception.CartException;
import com.OnlineShoppingApp.Exception.CustomerException;
import com.OnlineShoppingApp.Exception.ProductException;
import com.OnlineShoppingApp.Repository.CartDao;
import com.OnlineShoppingApp.Repository.CartProductDao;
import com.OnlineShoppingApp.Repository.CustomerDao;
import com.OnlineShoppingApp.Repository.ProductDao;
import com.OnlineShoppingApp.Repository.SessionDao;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CartProductDao cpDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Override
	public Cart addProductToCart(AddProductToCartDTO dto, String key) throws CartException,ProductException{
		CurrentSession validSession = sessionDao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("CUSTOMER")) {
			Optional<Cart> cartOpt = cartDao.findById(dto.getCartId());
			if(cartOpt.isEmpty()) throw new CartException("No such cart exist");
			
			Optional<Product> prodOpt = productDao.findById(dto.getProductId());
			if(prodOpt.isEmpty()) throw new ProductException("Product Does not exist");
					
			Cart existingCart = cartOpt.get();
			
			CartProduct cartProduct = new CartProduct();
			cartProduct.setProduct(prodOpt.get());
			cartProduct.setQuantity(dto.getQuantity());
			cartProduct.setCart(existingCart);
			
			existingCart.getCartProductList().add(cartProduct);

			return cartDao.save(existingCart);
		}
		throw new CartException("Invalid key or not of a customer.");
	}

	@Override
	public CartProduct removeProductFromCart(Integer cartProductId, String key) throws CartException{
		CurrentSession validSession = sessionDao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("CUSTOMER")) {
			Optional<CartProduct> cpOpt = cpDao.findById(cartProductId);
			if(cpOpt.isEmpty()) {
				throw new CartException("Invalid cart product id.");
			}
			CartProduct existingCp = cpOpt.get();
			cpDao.delete(existingCp);
			return existingCp;
		}
		throw new CartException("Invalid key or not of a customer.");
	}

	@Override
	public CartProduct updateProductQuantityInCart(Integer cartProductId, Integer newQuantity, String key) {
		CurrentSession validSession = sessionDao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("CUSTOMER")) {
			Optional<CartProduct> cpOpt = cpDao.findById(cartProductId);
			if(cpOpt.isPresent()) {
				CartProduct existingCartProd = cpOpt.get();
				existingCartProd.setQuantity(newQuantity);
				return cpDao.save(existingCartProd);
			}
			throw new CartException("No cart product found with this id: "+cartProductId);
		}
		throw new CartException("Invalid key or not of a customer.");
	}

	@Override
	public Cart removeAllProductsInCart(Integer customerId, String key) throws CartException,CustomerException{
		CurrentSession validSession = sessionDao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("CUSTOMER")) {
			Optional<Customer> customerOpt = customerDao.findById(customerId);
			if(customerOpt.isPresent()) {
				Customer existingCustomer = customerOpt.get();
				List<CartProduct> cartProducts = existingCustomer.getCart().getCartProductList();
				
				if(cartProducts.size()!=0) {
					for(int i=0;i<cartProducts.size();i++) {
						cpDao.delete(cartProducts.get(i));
					}
					return cartDao.save(existingCustomer.getCart());
				}
				throw new CartException("No product present in the cart of customer with id: "+customerId);
			}
			throw new CustomerException("Invalid customer id: "+customerId);
		}
		throw new CartException("Invalid key or not of a customer.");
	}

	@Override
	public List<CartProduct> viewAllProductsInCart(Integer customerId) throws CartException,CustomerException {
		Optional<Customer> customerOpt = customerDao.findById(customerId);
		if(customerOpt.isPresent()) {
			List<CartProduct> cartProducts = customerOpt.get().getCart().getCartProductList();
			if(cartProducts.size()!=0) {
				return cartProducts;
			}
			throw new CartException("No product present in the cart of customer with id: "+customerId);
		}
		throw new CustomerException("Invalid customer id: "+customerId);
	}
}
