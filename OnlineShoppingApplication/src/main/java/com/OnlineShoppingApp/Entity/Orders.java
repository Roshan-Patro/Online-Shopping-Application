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
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private LocalDate orderDate;
	private String orderStatus;
	
	@ManyToOne // Bidirectional
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL) // Unidirectional
	@JoinColumn(name = "ORDERID")
	private List<CartProduct> cartProductList;
	
	@ManyToOne // Bidirectional
	private Address address;

}
