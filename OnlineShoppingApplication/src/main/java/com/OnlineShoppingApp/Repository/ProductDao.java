package com.OnlineShoppingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingApp.Entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{

}
