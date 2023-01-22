package com.OnlineShoppingApp.DTO;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminRegisterDTO {
	
	@Size(min = 4, message = "admin company id must be of atleast 4 character length.")
    private String adminCompanyId;
	
	@Size(min = 5, message = "admin name must be of atleast 5 character length. Please, write your full name.")
    private String adminName;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{6,15}$",message = "password length must contain atleast 6 characters of atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;
}
