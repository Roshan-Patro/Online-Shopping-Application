package com.OnlineShoppingApp.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineShoppingApp.Entity.CartProduct;
import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Entity.Orders;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Exception.AddressException;
import com.OnlineShoppingApp.Exception.CustomerException;
import com.OnlineShoppingApp.Exception.OrdersException;
import com.OnlineShoppingApp.Repository.CustomerDao;
import com.OnlineShoppingApp.Repository.OrdersDao;
import com.OnlineShoppingApp.Repository.ProductDao;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	private OrdersDao odao;
	
	@Autowired
	private CustomerDao cdao;
	
	
	@Override
	public Orders addOrder(Orders order, Integer customerID, Integer addressId) throws OrdersException, CustomerException, AddressException {
		
		return null;
		
	}

	@Override
	public Orders updateOrder(Orders order) throws OrdersException {
		
		Orders existingOrder = odao.findById(order.getOrderId()).get();
		
		if(existingOrder == null) {
			throw new OrdersException("Order does not exists...");
		}
		if(existingOrder.getOrderStatus().equalsIgnoreCase("open")) {
			existingOrder.setOrderStatus("cancelled");
		}
		else if(existingOrder.getOrderStatus().equalsIgnoreCase("placed")) {
			throw new OrdersException("Order is already placed...");
		}
		else if(existingOrder.getOrderStatus().equalsIgnoreCase("cancelled")) {
			throw new OrdersException("This order is cancelled...");
		}
		
		Orders updatedOrders = odao.save(existingOrder);
		
		return updatedOrders;
		
	}

	@Override
	public Orders removeOrder(Orders order) throws OrdersException {
		
		Optional<Orders> existingOrder = odao.findById(order.getOrderId());
		
		if(existingOrder.isEmpty()) {
			throw new OrdersException("Order not found...");
		}
		else {
			
			Orders orders = existingOrder.get();
			
			orders.setOrderStatus("Returned");
			
			Orders updatedOrder = odao.save(orders);
			
			return updatedOrder;
			
		}
		
	}

	@Override
	public List<Orders> getAllOrderByOrderId(Integer orderId) throws OrdersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrder(LocalDate date) throws OrdersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrderByLocation(String loc) throws OrdersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrderByUserId(String userId) throws OrdersException {
		// TODO Auto-generated method stub
		return null;
	}

}
