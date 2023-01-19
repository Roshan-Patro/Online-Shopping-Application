package com.OnlineShoppingApp.Service;

import java.util.List;

import com.OnlineShoppingApp.Entity.Address;
import com.OnlineShoppingApp.Exception.AddressException;

public interface AddressService {
	
	// 1. Adding Address
	public Address addAnAddress(Address address) throws AddressException;
	
	// 2. Update Address
	public Address updateAddress(Address address) throws AddressException;
	
	// 3. Remove Address by address
	public Address removeAddress(Address address) throws AddressException;
	
	// 4. View all addresses
	public List<Address> viewAllAddresses() throws AddressException;
	
	// 5. View address by address Id
	public Address viewAddressByAddressId(Integer addressId) throws AddressException;
	
	// View addresses by building name
	
	// View addresses by country name
	
	// Find addresses of orders by pincode
	
	// Find addresses of customers by pincode
	
	// Find addresses of orders by Country and state
	
	// Find addresses of customers by Country and state
	
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
	
	
	
	
	
}
