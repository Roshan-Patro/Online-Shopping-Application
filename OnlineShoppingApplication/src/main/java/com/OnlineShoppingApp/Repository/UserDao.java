package com.OnlineShoppingApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingApp.Entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	public User findByCompIdEmail(String compIdEmail);

}
