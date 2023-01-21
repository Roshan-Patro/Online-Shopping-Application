package com.OnlineShoppingApp.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShoppingApp.DTO.AddressDTO;
import com.OnlineShoppingApp.Entity.Orders;
import com.OnlineShoppingApp.Service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersService oService;
	
	@PostMapping("/addOrderWithNewAdd/{cId}")
	public ResponseEntity<Orders> addOrderWithNewAddress(@PathVariable("cId") Integer customerId, @RequestBody AddressDTO dto, @RequestParam String key){
		return new ResponseEntity<>(oService.addOrderWithNewAddress(customerId, dto, key), HttpStatus.CREATED);
	}
	
	@GetMapping("/getByCustomerId/{cId}")
	public ResponseEntity<List<Orders>> getAllOrdersByCustomerId(@PathVariable("cId") Integer customerId){
		return new ResponseEntity<>(oService.getAllOrdersByCustomerId(customerId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getByOrderDate/{date}")
	public ResponseEntity<List<Orders>> getAllOrdersByDate(@PathVariable("date") LocalDate date){
		return new ResponseEntity<>(oService.getAllOrdersByDate(date), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getByOrderId/{id}")
	public ResponseEntity<Orders> getOrderByOrderId(@PathVariable("id") Integer id){
		return new ResponseEntity<>(oService.getOrderByOrderId(id), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/removeOrderById/{id}")
	public ResponseEntity<Orders> removeOrder(@PathVariable("id") Integer id, @RequestParam("key") String key){
		return new ResponseEntity<>(oService.removeOrder(id,key), HttpStatus.ACCEPTED);
	}
	
}
