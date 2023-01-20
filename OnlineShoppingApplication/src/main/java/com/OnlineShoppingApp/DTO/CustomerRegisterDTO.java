package com.OnlineShoppingApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRegisterDTO {
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private String password;
}
