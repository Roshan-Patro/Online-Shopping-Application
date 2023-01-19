package com.OnlineShoppingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingApp.Entity.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer>{

}
