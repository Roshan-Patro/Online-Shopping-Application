package com.OnlineShoppingApp.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {
	
	@NotNull(message = "streetNo count cannot be null.")
	private String streetNo;
	
	@Size(min = 4, message = "building name must be of atleast 4 character length.")
	private String buildingName;
	
	@Size(min = 2, message = "city name must be of atleast 2 character length.")
	private String city;
	
	@Size(min = 2, message = "state name must be of atleast 2 character length.")
	private String state;
	
	@Size(min = 2, message = "country name must be of atleast 2 character length.")
	private String country;
	
	@Pattern(regexp = "^[0-9]{6}",message="PinCode Length must be 6 digits [0-9]")
	private String pincode;
}
