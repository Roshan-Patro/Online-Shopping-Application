package com.OnlineShoppingApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingApp.Entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	public User findByCompIdEmail(String compIdEmail);
	
	@Query("select u from User u where u.compIdEmail=?1 and u.password=?2") // Not working
	public List<User> findBycompIdEmailAndpassword(String email, String password);

}
