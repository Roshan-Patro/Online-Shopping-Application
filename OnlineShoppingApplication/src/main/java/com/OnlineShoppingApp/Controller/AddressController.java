package com.OnlineShoppingApp.Controller;

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

import com.OnlineShoppingApp.Entity.Address;
import com.OnlineShoppingApp.Service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService aService;
	
	
	// 1. Adding Address
	@PostMapping
	public ResponseEntity<Address> addAnAddress(@RequestBody Address address){
		return new ResponseEntity<>(aService.addAnAddress(address), HttpStatus.CREATED);
	}
	
	// 2. Updating Address
	@PutMapping
	public ResponseEntity<Address> updateAddress(@RequestBody Address address){
		return new ResponseEntity<>(aService.updateAddress(address), HttpStatus.OK);
	}
	
	// 3. Removing Address by address
	@DeleteMapping
	public ResponseEntity<Address> removeAddress(@RequestBody Address address){
		return new ResponseEntity<>(aService.removeAddress(address), HttpStatus.OK);
	}
	
	// 4. Viewing all addresses
	@GetMapping
	public ResponseEntity<List<Address>> viewAllAddresses(){
		return new ResponseEntity<>(aService.viewAllAddresses(), HttpStatus.ACCEPTED);
	}
	
	// 5. Viewing address by address Id
	@GetMapping("/{addressId}")
	public ResponseEntity<Address> viewAddressByAddressId(@PathVariable("addressId") Integer addressId){
		return new ResponseEntity<>(aService.viewAddressByAddressId(addressId), HttpStatus.ACCEPTED);
	}
	
	// 6. Viewing addresses with sorting by a property in ascending order
	@GetMapping("/sortAsc")
	public ResponseEntity<List<Address>> viewAddressesWithSortingAsc(@RequestParam("sortBy") String sortBy){
		return new ResponseEntity<>(aService.viewAddressesWithSortingAsc(sortBy), HttpStatus.ACCEPTED);
	}
	
	// 7. Viewing addresses with sorting by a property in descending order
	@GetMapping("/sortDsc")
	public ResponseEntity<List<Address>> viewAddressesWithSortingDsc(@RequestParam("sortBy") String sortBy){
		return new ResponseEntity<>(aService.viewAddressesWithSortingDsc(sortBy), HttpStatus.ACCEPTED);
	}
	
	// 8. Viewing addresses with pagination and sorting by a property in ascending order
	@GetMapping("/paginateSortAsc")
	public ResponseEntity<List<Address>> viewAddressesWithPaginationAndSortingAsc(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam("sortBy") String sortBy){
		return new ResponseEntity<>(aService.viewAddressesWithPaginationAndSortingAsc(pageNo, pageSize, sortBy), HttpStatus.ACCEPTED);
	}
	
	// 9. Viewing addresses with pagination and sorting by a property in descending order
	@GetMapping("/paginateSortDsc")
	public ResponseEntity<List<Address>> viewAddressesWithPaginationAndSortingDsc(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam("sortBy") String sortBy){
		return new ResponseEntity<>(aService.viewAddressesWithPaginationAndSortingDsc(pageNo, pageSize, sortBy), HttpStatus.ACCEPTED);
	}
	
	// 10. Finding addresses by pincode
	@GetMapping("/getByPin/{pincode}")
	public ResponseEntity<List<Address>> findAddressesByPincode(@PathVariable("pincode") String pincode){
		return new ResponseEntity<>(aService.findAddressesByPincode(pincode), HttpStatus.ACCEPTED);
	}
	
	
}
