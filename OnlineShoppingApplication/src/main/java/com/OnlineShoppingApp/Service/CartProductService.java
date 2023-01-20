package com.OnlineShoppingApp.Service;

import java.util.List;

import com.OnlineShoppingApp.Entity.Cart;
import com.OnlineShoppingApp.Entity.CartProduct;
import com.OnlineShoppingApp.Entity.Product;

public interface CartProductService {
	public Cart addProductToCart(Cart cart,Product prod,Integer quantity);
	public Cart removeProductFromCart(Cart cart,Product prod);
	public Cart updateProductQuantity(Cart cart,Product prod,Integer quantity);
	public Cart removeAllProduct(Cart cart);
	public List<CartProduct> viewAllProducts(Integer customerId);
}
