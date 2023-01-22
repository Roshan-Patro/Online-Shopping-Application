package com.OnlineShoppingApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingApp.Entity.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

	// 1. Finding addresses by pincode
	public List<Address> findByPincode(String pincode);

	// 2. Finding addresses by building name
	public List<Address> findByBuildingName(String buildingName);

	// 3. Finding addresses by country and state name
	@Query("select a from Address a where a.country=?1 AND a.state=?2")
	public List<Address> findByCountryState(String country, String state);
	
	
	public List<Address> findByCity(String city);


}
