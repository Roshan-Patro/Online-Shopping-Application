package com.OnlineShoppingApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineShoppingApp.DAO.AddressDao;
import com.OnlineShoppingApp.Entity.Address;
import com.OnlineShoppingApp.Exception.AddressException;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressDao aDao;
	
	// Adding Address
	@Override
	public Address addAnAddress(Address address) throws AddressException {
		Address savedAddress = aDao.save(address);
		
		if(savedAddress!=null) {
			return savedAddress;
		}
		throw new AddressException("Address addition failed..!");
	}
	
	
	
}
