package com.OnlineShoppingApp.Exception;

public class CartProductException extends RuntimeException {
    public CartProductException() {}
    public CartProductException(String m) {
    	super(m);
    }
}
