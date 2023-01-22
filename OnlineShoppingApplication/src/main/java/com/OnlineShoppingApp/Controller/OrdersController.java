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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShoppingApp.DTO.AddressDTO;
import com.OnlineShoppingApp.Entity.Address;
import com.OnlineShoppingApp.Entity.Orders;
import com.OnlineShoppingApp.Service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersService oService;
	
	@PostMapping("/addOrderWithExstngAdd/{customerId}/{addressId}")
	public ResponseEntity<Orders> addOrderWithExstngAddress(@PathVariable("customerId") Integer customerId, @PathVariable("addressId") Integer addressId, @RequestParam String key){
		return new ResponseEntity<>(oService.addOrderWithExistingAddress(customerId, addressId, key), HttpStatus.CREATED);
	}
	
	@PostMapping("/addOrderWithNewAdd/{customerId}")
	public ResponseEntity<Orders> addOrderWithNewAddress(@PathVariable("customerId") Integer customerId, @RequestBody AddressDTO dto, @RequestParam String key){
		return new ResponseEntity<>(oService.addOrderWithNewAddress(customerId, dto, key), HttpStatus.CREATED);
	}
	
	@GetMapping("/getByCustomerId/{cId}")
	public ResponseEntity<List<Orders>> getAllOrdersByCustomerId(@PathVariable("cId") Integer customerId){
		return new ResponseEntity<>(oService.getAllOrdersByCustomerId(customerId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getByOrderDate/{date}")
	public ResponseEntity<List<Orders>> getAllOrdersByDate(@PathVariable("date") String date){
		return new ResponseEntity<>(oService.getAllOrdersByDate(date), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getByOrderId/{id}")
	public ResponseEntity<Orders> getOrderByOrderId(@PathVariable("id") Integer id){
		return new ResponseEntity<>(oService.getOrderByOrderId(id), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getByOrderCity/{cityName}")
	public ResponseEntity<List<Orders>> getAllOrderByCityName(@PathVariable("cityName") String city){
		return new ResponseEntity<>(oService.getAllOrderByCityName(city), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/removeOrderById/{orderId}")
	public ResponseEntity<Orders> removeOrder(@PathVariable("orderId") Integer orderId, @RequestParam("key") String key){
		return new ResponseEntity<>(oService.removeOrder(orderId,key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateOrderStat/{orderId}/{newStatus}")
	public ResponseEntity<Orders> updateOrderStatus(@PathVariable("orderId") Integer orderId, @PathVariable("newStatus") String newStat, @RequestParam("key") String key){
		return new ResponseEntity<>(oService.updateOrderStatus(orderId,newStat,key), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/sortAsc")
	public ResponseEntity<List<Orders>> viewOrdersWithSortingAsc(@RequestParam("sortBy") String sortBy) {
		return new ResponseEntity<>(oService.viewOrdersWithSortingAsc(sortBy), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/sortDesc")
	public ResponseEntity<List<Orders>> viewOrdersWithSortingDesc(@RequestParam("sortBy") String sortBy) {
		return new ResponseEntity<>(oService.viewOrdersWithSortingDesc(sortBy), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/paginateSortAsc")
	public ResponseEntity<List<Orders>> viewOrdersWithPaginationAndSortingAsc(
			@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("sortBy") String sortBy) {
		return new ResponseEntity<>(oService.viewOrdersWithPaginationAndSortingAsc(pageNo, pageSize, sortBy),
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/paginateSortDesc")
	public ResponseEntity<List<Orders>> viewOrdersWithPaginationAndSortingDesc(
			@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("sortBy") String sortBy) {
		return new ResponseEntity<>(oService.viewOrdersWithPaginationAndSortingDesc(pageNo, pageSize, sortBy),
				HttpStatus.ACCEPTED);
	}
}
