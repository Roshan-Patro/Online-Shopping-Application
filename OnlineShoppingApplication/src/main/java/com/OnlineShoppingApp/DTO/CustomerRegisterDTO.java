package com.OnlineShoppingApp.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRegisterDTO {
	
	@Size(min = 3, message = "first name must be of atleast 3 character length.")
	private String firstName;
	
	@Size(min = 3, message = "last name must be of atleast 3 character length.")
	private String lastName;
	
	@Pattern(regexp = "^[0-9]{10}",message="Mobile number length must be 10 digits [0-9]")
	private String mobileNumber;
	
	@Email(message = "Email must be a valid one.")
	private String email;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{6,15}$",message = "password length must contain atleast 6 characters of atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String password;
}
