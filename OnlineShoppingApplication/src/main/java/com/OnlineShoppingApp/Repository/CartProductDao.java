package com.OnlineShoppingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingApp.Entity.CartProduct;

@Repository
public interface CartProductDao extends JpaRepository<CartProduct, Integer>{

}
