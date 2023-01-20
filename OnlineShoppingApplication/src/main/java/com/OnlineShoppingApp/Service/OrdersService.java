package com.OnlineShoppingApp.Service;

import java.time.LocalDate;
import java.util.List;

import com.OnlineShoppingApp.Entity.Orders;
import com.OnlineShoppingApp.Exception.AddressException;
import com.OnlineShoppingApp.Exception.CustomerException;
import com.OnlineShoppingApp.Exception.OrdersException;

public interface OrdersService {
	
	public Orders addOrder(Orders order, Integer customerID, Integer addressId) throws OrdersException, CustomerException, AddressException;
	
	public Orders updateOrder(Orders order) throws OrdersException;
	
	public Orders removeOrder(Orders order) throws OrdersException;
	
	public List<Orders> getAllOrderByOrderId(Integer orderId) throws OrdersException;
	
	public List<Orders> getAllOrder(LocalDate date) throws OrdersException;
	
	public List<Orders> getAllOrderByLocation(String loc) throws OrdersException;
	
	public List<Orders> getAllOrderByUserId(String userId) throws OrdersException;
	
}
