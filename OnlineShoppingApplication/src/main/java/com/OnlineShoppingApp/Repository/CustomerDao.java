package com.OnlineShoppingApp.Repository;

import com.OnlineShoppingApp.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer> {
    public Customer findByEmail(String email);
    
}
