package com.OnlineShoppingApp.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
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
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@Size(min = 3, message = "first name must be of atleast 3 character length.")
	private String firstName;
	
	@Size(min = 3, message = "last name must be of atleast 3 character length.")
	private String lastName;
	
	@Pattern(regexp = "^[0-9]{10}",message="Mobile number length must be 10 digits [0-9]")
	private String mobileNumber;
	
	@Email(message = "Email must be a valid one.")
	private String email;
	
//	@JsonIgnore
//	@ManyToMany(cascade = CascadeType.ALL) // Always Bidirectional
//	private List<Address> addressList;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") // Bidirectional
	private List<Address> addressList;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "customer") //Bidirectional
	private Cart cart;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") // Bidirectional
	private List<Orders> orderList;
}
