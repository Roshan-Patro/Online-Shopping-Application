package com.OnlineShoppingApp.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.OnlineShoppingApp.Enum.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
	@Id
	private Integer userId;
	
	private String compIdEmail;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
