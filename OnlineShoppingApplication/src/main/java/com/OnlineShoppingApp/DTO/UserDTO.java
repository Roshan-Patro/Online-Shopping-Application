package com.OnlineShoppingApp.DTO;

import lombok.*;

@Data
public class UserDTO {
	private String email;
	private String password;
	
	// Later we will use enum here for role
//	private String role;
}
