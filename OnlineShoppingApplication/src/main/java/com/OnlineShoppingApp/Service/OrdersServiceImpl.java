package com.OnlineShoppingApp.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.OnlineShoppingApp.DTO.AddressDTO;
import com.OnlineShoppingApp.Entity.Address;
import com.OnlineShoppingApp.Entity.CartProduct;
import com.OnlineShoppingApp.Entity.CurrentSession;
import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Entity.Orders;
import com.OnlineShoppingApp.Entity.Product;
import com.OnlineShoppingApp.Enum.CartProductStatus;
import com.OnlineShoppingApp.Enum.Role;
import com.OnlineShoppingApp.Enum.Status;
import com.OnlineShoppingApp.Exception.AddressException;
import com.OnlineShoppingApp.Exception.AdminException;
import com.OnlineShoppingApp.Exception.CartException;
import com.OnlineShoppingApp.Exception.CustomerException;
import com.OnlineShoppingApp.Exception.OrdersException;
import com.OnlineShoppingApp.Repository.AddressDao;
import com.OnlineShoppingApp.Repository.CartProductDao;
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
	
	@Autowired
	private CartProductDao cpDao;

	@Override
	public Orders addOrderWithExistingAddress(Integer customerId, Integer addressId, String key)
			throws OrdersException, CustomerException, AddressException {

		CurrentSession validSession = sessionDao.findByUuid(key);
		if(validSession!=null && validSession.getRole().toString().equals("CUSTOMER")) {
			Optional<Customer> customerOpt = cdao.findById(customerId);
			if(customerOpt.isPresent()) {
				Customer existingCustomer = customerOpt.get();
				List<CartProduct> allProductsInCart = existingCustomer.getCart().getCartProductList();
				List<CartProduct> unorderedCartProducts = new ArrayList<>();
				for(CartProduct cp:allProductsInCart) {
					if(cp.getCpStatus().toString().equals("UNORDERED")) {
						unorderedCartProducts.add(cp);
					}
				}
				int unorderedCartSize = unorderedCartProducts.size();
				if (unorderedCartSize != 0) {
					Optional<Address> addOpt = addDao.findById(addressId);
					if(addOpt.isPresent()) {
//						Address existingAddress = addOpt.get();
						List<Address> customerAddresses = existingCustomer.getAddressList();
						Address addressForOrder = new Address();
						for(Address add:customerAddresses) {
							if(add.getAddressId()==addressId) {
								addressForOrder = add;
							}
						}
						if(addressForOrder!=null) {
							Orders orders = new Orders();
							orders.setOrderDate(LocalDate.now());
							orders.setOrderStatus("PENDING");
							orders.setCartProductList(unorderedCartProducts);
							
							for(CartProduct cp:unorderedCartProducts) {
								cp.setCpStatus(CartProductStatus.ORDERED);
							}

							orders.setAddress(addressForOrder);
							if (addressForOrder.getOrderList() == null) {
								List<Orders> orderListForAddress = new ArrayList<>();
								orderListForAddress.add(orders);
								addressForOrder.setOrderList(orderListForAddress);
							} else {
								addressForOrder.getOrderList().add(orders);
							}
							
							orders.setCustomer(existingCustomer);
							if(existingCustomer.getOrderList()==null) {
								List<Orders> orderListForCustomer = new ArrayList<>();
								orderListForCustomer.add(orders);
								existingCustomer.setOrderList(orderListForCustomer);
							} else {
								existingCustomer.getOrderList().add(orders);
							}

							Customer savedCustomer = cdao.save(existingCustomer);
							List<Orders> orderList = savedCustomer.getOrderList();
							return orderList.get(orderList.size()-1);
							
						}
						throw new AddressException("This address is not registered with this customer. Please add this address to this customer.");
					}
					throw new AddressException("Invalid address id: "+addressId);
				}
				throw new OrdersException("No unordered cart product found in cart.");
			}
			throw new CustomerException("Invalid customer id: "+customerId);
		}
		throw new CustomerException("Invalid key or not of a customer.");
	}
	
	
	@Override
	public Orders addOrderWithNewAddress(Integer customerId, AddressDTO dto, String key)
			throws CustomerException, OrdersException {
		CurrentSession validSession = sessionDao.findByUuid(key);
		if (validSession != null && validSession.getRole().toString().equals("CUSTOMER")) {
			Optional<Customer> customerOpt = cdao.findById(customerId);
			if (customerOpt.isPresent()) {
				Customer existingCustomer = customerOpt.get();
				List<CartProduct> allProductsInCart = existingCustomer.getCart().getCartProductList();
				List<CartProduct> unorderedCartProducts = new ArrayList<>();
				for(CartProduct cp:allProductsInCart) {
					if(cp.getCpStatus().toString().equals("UNORDERED")) {
						unorderedCartProducts.add(cp);
					}
				}
				int unorderedCartSize = unorderedCartProducts.size();
				if (unorderedCartSize != 0) {
					Address address = new Address();
					address.setBuildingName(dto.getBuildingName());
					address.setCity(dto.getCity());
					address.setCountry(dto.getCountry());
					address.setState(dto.getState());
					address.setStreetNo(dto.getStreetNo());
					address.setPincode(dto.getPincode());

					Orders orders = new Orders();
					orders.setOrderDate(LocalDate.now());
					orders.setOrderStatus("PENDING");
					orders.setCartProductList(unorderedCartProducts);
					
					for(CartProduct cp:unorderedCartProducts) {
						cp.setCpStatus(CartProductStatus.ORDERED);
					}

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

//					if (address.getCustomerList() == null) {
//						List<Customer> customerList = new ArrayList<>();
//						customerList.add(existingCustomer);
//						address.setCustomerList(customerList);
//					} else {
//						address.getCustomerList().add(existingCustomer);
//					}

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
				throw new OrdersException("No unordered cart product found in cart.");
			}
			throw new CustomerException("No customer found with id: " + customerId);
		}
		throw new OrdersException("Invalid key or not of a customer...!");
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
	public List<Orders> getAllOrdersByDate(String date) throws OrdersException {
		LocalDate localDate = LocalDate.parse(date);
		List<Orders> orderList = odao.findByOrderDate(localDate);
		if(orderList.size()!=0) {
			return orderList;
		}
		throw new OrdersException("No order found on this date: "+date);
	}

	@Override
	public List<Orders> getAllOrderByCityName(String city) throws OrdersException {
		
		List<Address> addressList = addDao.findByCity(city);
		if(addressList.size()==0) {
			throw new OrdersException("No address found with this city name: "+city);
		}
		
		List<Orders> orderList = new ArrayList<>();
		for(Address address:addressList) {
			List<Orders> subOrderList = address.getOrderList();
			if(subOrderList.size()!=0) {
				for(Orders order:subOrderList) {
					orderList.add(order);
				}
			}
		}
		if(orderList.size()!=0) {
			return orderList;
		}
		throw new OrdersException("No order found for this city: "+city);
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
	public Orders updateOrderStatus(Integer orderId, String newStatus, String key)
			throws OrdersException, AdminException {
		CurrentSession validSession = sessionDao.findByUuid(key);
		if (validSession != null && validSession.getRole().toString().equals(Role.ADMIN.toString())) {
			Optional<Orders> orderOpt = odao.findById(orderId);
			if(orderOpt.isPresent()) {
				Orders existingOrder = orderOpt.get();
				existingOrder.setOrderStatus(newStatus);
				List<CartProduct> linkedCartProducts = existingOrder.getCartProductList();
				while(linkedCartProducts.size()>0) {
					Optional<CartProduct> cpOpt = cpDao.findById(linkedCartProducts.get(0).getCartProductId());
					CartProduct existingCp = cpOpt.get();
					linkedCartProducts.remove(0);
					cpDao.delete(existingCp);
				}
				return existingOrder;
			}
			throw new OrdersException("Invlid order id..!");
		}
		throw new AdminException("Invalid key or not of an admin...!");
	}
	
	@Override
	public List<Orders> viewOrdersWithSortingAsc(String sortBy) throws OrdersException {
		List<Orders> orderList = odao.findAll(Sort.by(Direction.ASC, sortBy));
		if (orderList.size() != 0) {
			return orderList;
		}
		throw new OrdersException("No details found. Either the property is invalid or no records available.");
	}

	@Override
	public List<Orders> viewOrdersWithSortingDesc(String sortBy) throws OrdersException {
		List<Orders> orderList = odao.findAll(Sort.by(Direction.DESC, sortBy));
		if (orderList.size() != 0) {
			return orderList;
		}
		throw new OrdersException("No details found. Either the property is invalid or no records available.");
	}
	
	@Override
	public List<Orders> viewOrdersWithPaginationAndSortingAsc(Integer pageNo, Integer pageSize, String sortBy)
			throws OrdersException {
		Pageable pObj = PageRequest.of(pageNo, pageSize, Sort.by(Direction.ASC, sortBy));

		if (pObj.isPaged()) {
			Page<Orders> ordersPage = odao.findAll(pObj);

			List<Orders> allOrders = ordersPage.getContent();

			return allOrders;
		}
		throw new OrdersException(
				"No result found for this request...! Please, try again with another set of instructions! :)");

	}
	
	@Override
	public List<Orders> viewOrdersWithPaginationAndSortingDesc(Integer pageNo, Integer pageSize, String sortBy)
			throws OrdersException {
		Pageable pObj = PageRequest.of(pageNo, pageSize, Sort.by(Direction.DESC, sortBy));

		if (pObj.isPaged()) {
			Page<Orders> ordersPage = odao.findAll(pObj);

			List<Orders> allOrders = ordersPage.getContent();

			return allOrders;
		}
		throw new OrdersException(
				"No result found for this request...! Please, try again with another set of instructions! :)");

	}
}
