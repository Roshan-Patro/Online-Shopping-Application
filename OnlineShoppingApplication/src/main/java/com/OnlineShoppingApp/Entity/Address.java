package com.OnlineShoppingApp.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	// @Size(min = 3, message = "Admin name must be of atleast 3 character length.")
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
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "address") // Bidirectional
	private List<Orders> orderList; 
	
//	@JsonIgnore
//	@ManyToMany(mappedBy = "addressList") // Always Bidirectional
//	private List<Customer> customerList;
	
	@JsonIgnore
	@ManyToOne // Bidirectional
	private Customer customer;

}
