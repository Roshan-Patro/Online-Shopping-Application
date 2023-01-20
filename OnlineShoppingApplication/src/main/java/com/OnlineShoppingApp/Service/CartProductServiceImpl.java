package com.OnlineShoppingApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineShoppingApp.Entity.Cart;
import com.OnlineShoppingApp.Entity.CartProduct;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Exception.CartException;
import com.OnlineShoppingApp.Repository.CartDao;
import com.OnlineShoppingApp.Repository.CustomerDao;
import com.OnlineShoppingApp.Repository.ProductDao;

@Service
public class CartProductServiceImpl implements CartProductService{
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public Cart addProductToCart(Cart cart, Product prod, Integer quantity) throws CartException{
		Optional<Cart> opt1=cartDao.findById(cart.getCartId());
		if(opt1.isEmpty()) throw new CartException("No such cart exist");
		
//		Optional<Product> opt2=productDao.findById(prod.getProductId());
//		If(opt2.isEmpty()) throw new ProductException("Product Does not exist");
				
		Cart cart1=opt1.get();
		CartProduct cp=new CartProduct();
		cp.setProduct(prod);
		cp.setQuantity(quantity);
		cart1.getCartProductList().add(cp);
		return cart1;
	}

	@Override
	public Cart removeProductFromCart(Cart cart, Product prod) {
		// TODO Auto-generated method stub
		cart.getCartProductList().removeIf(i->(i.getProduct().equals(prod)));
		cartDao.save(cart);
		return cart;
	}

	@Override
	public Cart updateProductQuantity(Cart cart, Product prod, Integer quantity) {
		for(CartProduct i:cart.getCartProductList()) {
			if(i.getProduct().equals(prod)) {
				i.setQuantity(quantity);
			}
		}
		cartDao.save(cart);
		return cart;
	}

	@Override
	public Cart removeAllProduct(Cart cart) {
		cart.getCartProductList().clear();
		cartDao.save(cart);
		return cart;
	}

	@Override
	public List<CartProduct> viewAllProducts(Integer customerId) {
		return null;
	}
}
