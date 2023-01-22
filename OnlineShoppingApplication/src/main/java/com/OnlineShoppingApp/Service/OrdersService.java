package com.OnlineShoppingApp.Service;

import java.time.LocalDate;
import java.util.List;

import com.OnlineShoppingApp.DTO.AddressDTO;
import com.OnlineShoppingApp.Entity.Orders;
import com.OnlineShoppingApp.Exception.AddressException;
import com.OnlineShoppingApp.Exception.AdminException;
import com.OnlineShoppingApp.Exception.CustomerException;
import com.OnlineShoppingApp.Exception.OrdersException;

public interface OrdersService {
	
	public Orders addOrderWithExistingAddress(Integer customerId, Integer addressId, String key) throws OrdersException, CustomerException, AddressException;
	
	public Orders addOrderWithNewAddress(Integer customerId, AddressDTO dto, String key) throws CustomerException,OrdersException;
	
	public Orders updateOrderStatus(Integer orderId, String newStatus, String key) throws OrdersException, AdminException;
	
	public Orders updateOrder(Orders order) throws OrdersException;
	
	public Orders removeOrder(Integer orderId, String key) throws OrdersException;
	
	public Orders getOrderByOrderId(Integer orderId) throws OrdersException;
	
	public List<Orders> getAllOrdersByDate(String date) throws OrdersException;
	
	public List<Orders> getAllOrderByCityName(String city) throws OrdersException;
	
	public List<Orders> getAllOrdersByCustomerId(Integer customerId) throws OrdersException,CustomerException;
	
	public List<Orders> viewOrdersWithSortingAsc(String sortBy) throws OrdersException;
	
	public List<Orders> viewOrdersWithSortingDesc(String sortBy) throws OrdersException;
	
	public List<Orders> viewOrdersWithPaginationAndSortingAsc(Integer pageNo, Integer pageSize, String sortBy)
			throws OrdersException;
	
	public List<Orders> viewOrdersWithPaginationAndSortingDesc(Integer pageNo, Integer pageSize, String sortBy)
			throws OrdersException;
}
