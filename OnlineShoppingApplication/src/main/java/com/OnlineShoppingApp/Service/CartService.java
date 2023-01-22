package com.OnlineShoppingApp.Service;

import java.util.List;

import com.OnlineShoppingApp.DTO.AddProductToCartDTO;
import com.OnlineShoppingApp.Entity.Cart;
import com.OnlineShoppingApp.Entity.CartProduct;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Exception.CartException;
import com.OnlineShoppingApp.Exception.CustomerException;
import com.OnlineShoppingApp.Exception.ProductException;

public interface CartService {
	public CartProduct addProductToCart(AddProductToCartDTO dto, String key) throws CartException,ProductException;
	public CartProduct removeProductFromCart(Integer cartProductId, String key) throws CartException;
	public CartProduct updateProductQuantityInCart(Integer cartProductId, Integer newQuantity, String key);
	public List<CartProduct> removeAllProductsInCart(Integer customerId, String key) throws CartException,CustomerException;
	public List<CartProduct> viewAllProductsInCart(Integer customerId) throws CartException,CustomerException;
	public Double findTotalCartPriceByCustomerId(Integer customerId) throws CartException;
}
