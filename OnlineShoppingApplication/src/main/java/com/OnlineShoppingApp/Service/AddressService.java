package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.Entity.Address;
import com.OnlineShoppingApp.Exception.AddressException;

public interface AddressService {

	// Adding Address
	public Address addAnAddress(Address address) throws AddressException;
}
