package com.OnlineShoppingApp.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineShoppingApp.DTO.AddressDTO;
import com.OnlineShoppingApp.Entity.Address;
import com.OnlineShoppingApp.Entity.CartProduct;
import com.OnlineShoppingApp.Entity.CurrentSession;
import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Entity.Orders;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Enum.Status;
import com.OnlineShoppingApp.Exception.AddressException;
import com.OnlineShoppingApp.Exception.CartException;
import com.OnlineShoppingApp.Exception.CustomerException;
import com.OnlineShoppingApp.Exception.OrdersException;
import com.OnlineShoppingApp.Repository.AddressDao;
import com.OnlineShoppingApp.Repository.CustomerDao;
import com.OnlineShoppingApp.Repository.OrdersDao;
import com.OnlineShoppingApp.Repository.ProductDao;
import com.OnlineShoppingApp.Repository.SessionDao;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao odao;

	@Autowired
	private CustomerDao cdao;

	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private AddressDao addDao;

	@Override
	public Orders addOrder(Orders order, Integer customerID, Integer addressId)
			throws OrdersException, CustomerException, AddressException {

		return null;

	}

	@Override
	public Orders updateOrder(Orders order) throws OrdersException {

//		Orders existingOrder = odao.findById(order.getOrderId()).get();
//		
//		if(existingOrder == null) {
//			throw new OrdersException("Order does not exists...");
//		}
//		if(existingOrder.getOrderStatus().equalsIgnoreCase("open")) {
//			existingOrder.setOrderStatus("cancelled");
//		}
//		else if(existingOrder.getOrderStatus().equalsIgnoreCase("placed")) {
//			throw new OrdersException("Order is already placed...");
//		}
//		else if(existingOrder.getOrderStatus().equalsIgnoreCase("cancelled")) {
//			throw new OrdersException("This order is cancelled...");
//		}
//		
//		Orders updatedOrders = odao.save(existingOrder);
//		
//		return updatedOrders;
		return null;

	}

	@Override
	public Orders removeOrder(Integer orderId, String key) throws OrdersException {
		CurrentSession validSession = sessionDao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("CUSTOMER")) {
			Optional<Orders> orderOpt = odao.findById(orderId);
			
			if(orderOpt.isEmpty()) {
				throw new OrdersException("Order not found...");
			}
			else {
				
				Orders orders = orderOpt.get();
				
				odao.delete(orders);
				
				return orders;
				
			}
		}
		throw new CartException("Invalid key or not of a customer.");
	}

	@Override
	public Orders getOrderByOrderId(Integer orderId) throws OrdersException {
		Optional<Orders> orderOpt = odao.findById(orderId);
		if(orderOpt.isPresent()) {
			return orderOpt.get();
		}
		throw new OrdersException("No order found with this id: "+orderId);
	}

	@Override
	public List<Orders> getAllOrdersByDate(LocalDate date) throws OrdersException {
		List<Orders> orderList = odao.findByOrderDate(date);
		if(orderList.size()!=0) {
			return orderList;
		}
		throw new OrdersException("No order found on this date: "+date);
	}

	@Override
	public List<Orders> getAllOrderByCityName(String city) throws OrdersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrdersByCustomerId(Integer customerId) throws OrdersException,CustomerException {
		Optional<Customer> customerOpt = cdao.findById(customerId);
		if(customerOpt.isPresent()) {
			List<Orders> orderList = customerOpt.get().getOrderList();
			if(orderList.size()!=0) {
				return orderList;
			}
			throw new OrdersException("No order found for customer with id: "+customerId);
		}
		throw new CustomerException("No customer found with id: "+customerId);
	}

	@Override
	public Orders addOrderWithNewAddress(Integer customerId, AddressDTO dto, String key)
			throws CustomerException, OrdersException {
		CurrentSession validSession = sessionDao.findByUuid(key);
		if (validSession != null && validSession.getRole().toString().equals("CUSTOMER")) {
			Optional<Customer> customerOpt = cdao.findById(customerId);
			if (customerOpt.isPresent()) {
				Customer existingCustomer = customerOpt.get();
				int cartSize = existingCustomer.getCart().getCartProductList().size();
				if (cartSize != 0) {
					Address address = new Address();
					address.setBuildingName(dto.getBuildingName());
					address.setCity(dto.getCity());
					address.setCountry(dto.getCountry());
					address.setState(dto.getState());
					address.setStreetNo(dto.getStreetNo());
					address.setPincode(dto.getPincode());

					Orders orders = new Orders();
					orders.setOrderDate(LocalDate.now());
					orders.setOrderStatus(Status.PENDING);
					orders.setCartProductList(existingCustomer.getCart().getCartProductList());

					orders.setAddress(address);
					if (address.getOrderList() == null) {
						List<Orders> orderListForAddress = new ArrayList<>();
						orderListForAddress.add(orders);
						address.setOrderList(orderListForAddress);
					} else {
						address.getOrderList().add(orders);
					}
					
					orders.setCustomer(existingCustomer);
					if(existingCustomer.getOrderList()==null) {
						List<Orders> orderListForCustomer = new ArrayList<>();
						orderListForCustomer.add(orders);
						existingCustomer.setOrderList(orderListForCustomer);
					} else {
						existingCustomer.getOrderList().add(orders);
					}

					if (address.getCustomerList() == null) {
						List<Customer> customerList = new ArrayList<>();
						customerList.add(existingCustomer);
						address.setCustomerList(customerList);
					} else {
						address.getCustomerList().add(existingCustomer);
					}

					if (existingCustomer.getAddressList() == null) {
						List<Address> addressList = new ArrayList<>();
						addressList.add(address);
						existingCustomer.setAddressList(addressList);
					} else {
						existingCustomer.getAddressList().add(address);
					}

					Customer savedCustomer = cdao.save(existingCustomer);
					List<Orders> orderList = savedCustomer.getOrderList();
					return orderList.get(orderList.size()-1);
				}
				throw new OrdersException("No cart product found in cart. First add some.");
			}
			throw new CustomerException("No customer found with id: " + customerId);
		}
		throw new OrdersException("Invalid key or not of a customer...!");
	}

}
