package com.OnlineShoppingApp.Exception;

public class CartException extends RuntimeException {
    public CartException() {}
    public CartException(String m) {
    	super(m);
    }
}
