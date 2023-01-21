package com.OnlineShoppingApp.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingApp.Entity.Orders;

@Repository
public interface OrdersDao extends JpaRepository<Orders, Integer>{
	
	public List<Orders> findByOrderDate(LocalDate orderDate);
}
