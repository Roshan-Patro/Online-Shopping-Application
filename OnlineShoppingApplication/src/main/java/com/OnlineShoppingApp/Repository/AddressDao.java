package com.OnlineShoppingApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingApp.Entity.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer>{
	
	// 1. Finding addresses by pincode
	public List<Address> findByPincode(String pincode);

}
