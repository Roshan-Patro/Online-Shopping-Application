package com.OnlineShoppingApp.Controller;

import com.OnlineShoppingApp.DTO.CustomerRegisterDTO;
import com.OnlineShoppingApp.DTO.CustomerUpdateDTO;
import com.OnlineShoppingApp.DTO.UpdatePasswordDTO;
import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Exception.CustomerException;
import com.OnlineShoppingApp.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService cServ;

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRegisterDTO customerRegisterDTO){
        Customer add= cServ.addCustomer(customerRegisterDTO);
        return new ResponseEntity<Customer>(add, HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewByCustomerId/{customer_Id}")
    public ResponseEntity<Customer> viewCustomerById(@PathVariable Integer customerId){
        Customer view= cServ.viewCustomerById(customerId);
        return new ResponseEntity<Customer>(view, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateCustomer/{customer_Id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdtDto, @PathVariable String key){
        Customer update= cServ.updateCustomer(customerUpdtDto, key);
        return new ResponseEntity<Customer>(update, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteCustomer/{customer_Id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable Integer customerId){
        Customer delete= cServ.deleteCustomerById(customerId);
        return new ResponseEntity<Customer>(delete, HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewAllCustomer")
    public ResponseEntity<List<Customer>> viewAllCustomer(){
        List<Customer> viewAll = cServ.viewAllConsumer();
        return new ResponseEntity<List<Customer>>(viewAll, HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/updateCustomerPassword")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordDTO dto){
        String updatePass= cServ.updatePassword(dto);
        return new ResponseEntity<String>(updatePass, HttpStatus.ACCEPTED);
    }


}
