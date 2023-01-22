package com.OnlineShoppingApp.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerUpdateDTO {
	
	@Min(value = 1, message = "customerId cannot be less than 1")
	private Integer customerId;
	
	@Size(min = 3, message = "first name must be of atleast 3 character length.")
	private String firstName;
	
	@Size(min = 3, message = "last name must be of atleast 3 character length.")
	private String lastName;
	
	@Pattern(regexp = "^[0-9]{10}",message="Mobile number length must be 10 digits [0-9]")
	private String mobileNumber;
	
	@Email(message = "Email must be a valid one.")
	private String email;
}
