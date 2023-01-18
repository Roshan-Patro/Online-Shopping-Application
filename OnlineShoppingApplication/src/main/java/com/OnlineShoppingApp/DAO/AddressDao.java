package com.OnlineShoppingApp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingApp.Entity.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer>{

}
