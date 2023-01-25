package com.OnlineShoppingApp.DTO;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminLogInDTO {
	
	@Size(min = 4, message = "Admin's company id must be of atleast 4-character length.")
	private String adminCompanyId;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{6,15}$",message = "Password length must contain atleast 6 characters with atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit.")
	private String password;
}
