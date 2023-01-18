package com.OnlineShoppingApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShoppingApp.Entity.Address;
import com.OnlineShoppingApp.Service.AddressService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService aService;
	
	@PostMapping
	public ResponseEntity<Address> addAnAddress(@RequestBody Address address){
		return new ResponseEntity<>(aService.addAnAddress(address), HttpStatus.CREATED);
	}
}
