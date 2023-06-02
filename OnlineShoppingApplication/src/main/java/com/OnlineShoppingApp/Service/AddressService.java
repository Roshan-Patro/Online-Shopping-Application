package com.OnlineShoppingApp.Service;

import java.util.List;

import com.OnlineShoppingApp.Entity.Address;
import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Exception.AddressException;
import com.OnlineShoppingApp.Exception.CustomerException;

public interface AddressService {
	
	// 1. Adding Address
	public Address addAnAddress(Address address) throws AddressException;
	
	// 2. Update Address
	public Address updateAddress(Address address) throws AddressException;
	
	// 3. Remove Address by address Id
	public Address removeAddressById(Integer addressId) throws AddressException;
	
	// 4. View all addresses
	public List<Address> viewAllAddresses() throws AddressException;
	
	// 5. View address by address Id
	public Address viewAddressByAddressId(Integer addressId) throws AddressException;
	
	// 6. View addresses by building name
	public List<Address> viewAddressesByBuildingName(String buildingName) throws AddressException;
	
	// 7. View addresses of orders by pincode
	public List<Address> addressesOfOrdersByPin(String pincode) throws AddressException;
	
	// 8. View addresses of customers by pincode
	public List<Address> addressesOfCustomersByPin(String pincode) throws AddressException;
	
	// 9. View addresses of orders by Country and State
	public List<Address> addressesOfOrdersByCountryState(String country, String state) throws AddressException;
	
	// 10. View addresses of customers by Country and State
	public List<Address> addressesOfCustomersByCountryState(String country, String state) throws AddressException;
	
	// View top n locations of a country according to number of orders placed
	
	// View last n locations of a country according to number of oreders placed
	
	// 6. View addresses with sorting by a property in ascending order
	public List<Address> viewAddressesWithSortingAsc(String sortBy) throws AddressException;
	
	// 7. View addresses with sorting by a property in descending order
	public List<Address> viewAddressesWithSortingDsc(String sortBy) throws AddressException;
	
	// 8. View addresses with pagination and sorting by a property in ascending order
	public List<Address> viewAddressesWithPaginationAndSortingAsc(Integer pageNo, Integer pageSize, String sortBy) throws AddressException;
	
	// 9. View addresses with pagination and sorting by a property in descending order
	public List<Address> viewAddressesWithPaginationAndSortingDsc(Integer pageNo, Integer pageSize, String sortBy) throws AddressException;
	
	// 10. Find addresses by pincode
	public List<Address> findAddressesByPincode(String pincode) throws AddressException;
	
	public Customer addAnAddressToCustomer(Integer addressId, Integer customerId) throws AddressException,CustomerException;
	
	
	
}
