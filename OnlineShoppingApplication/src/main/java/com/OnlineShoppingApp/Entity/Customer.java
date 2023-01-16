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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private String password;
	
	@ManyToMany // Always Bidirectional
	private List<Address> addressList;
	
	@OneToOne(mappedBy = "customer") //Bidirectional
	private Cart cart;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") // Bidirectional
	private List<Orders> orderList;
}
