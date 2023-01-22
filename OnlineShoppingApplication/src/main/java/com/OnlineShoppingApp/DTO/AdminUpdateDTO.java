package com.OnlineShoppingApp.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminUpdateDTO {
	
	@Min(value = 1, message = "dose count cannot be less than 1")
	private Integer adminLoginId;
	
	@Size(min = 4, message = "admin company id must be of atleast 4 character length.")
    private String adminCompanyId;
	
	@Size(min = 5, message = "admin name must be of atleast 5 character length. Please, write your full name.")
    private String adminName;
}
