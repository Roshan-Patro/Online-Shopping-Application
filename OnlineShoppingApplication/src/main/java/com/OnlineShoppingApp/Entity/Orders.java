package com.OnlineShoppingApp.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	private LocalDate orderDate;
	private String orderStatus;
	
	@JsonIgnore
	@ManyToOne // Bidirectional
	private Customer customer;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL) // Unidirectional
	@JoinColumn(name = "ORDERID")
	private List<CartProduct> cartProductList;
	
	@JsonIgnore
	@ManyToOne // Bidirectional
	private Address address;
}
