package com.OnlineShoppingApp.Controller;

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
    public ResponseEntity<Customer>addCustomer(@RequestBody Customer customer)throws CustomerException {
        Customer add= cServ.addCustomer(customer);
        return new ResponseEntity<Customer>(add, HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewByCustomerId/{customer_Id}")
    public ResponseEntity<Customer>addCustomer(@PathVariable Integer customer_Id)throws CustomerException {
        Customer view= cServ.viewCustomer(customer_Id);
        return new ResponseEntity<Customer>(view, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateCustomer/{customer_Id}")
    public ResponseEntity<Customer>updateCustomer(@RequestBody Customer customer,@PathVariable Integer customer_Id)throws CustomerException{
        Customer update= cServ.updateCustomer(customer,customer_Id);
        return new ResponseEntity<Customer>(update, HttpStatus.ACCEPTED);
    }

    @PutMapping("/deleteCustomer/{customer_Id}")
    public ResponseEntity<Customer>deleteCustomer(@PathVariable Integer customer_Id)throws CustomerException{
        Customer delete= cServ.deleteCustomer(customer_Id);
        return new ResponseEntity<Customer>(delete, HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewAllCustomer")
    public ResponseEntity<List<Customer>>viewAllCustomer()throws CustomerException{
        List<Customer> viewAll= cServ.viewAllConsumer();
        return new ResponseEntity<List<Customer>>(viewAll, HttpStatus.ACCEPTED);
    }


}
