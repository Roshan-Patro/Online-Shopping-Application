package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.DTO.CustomerRegisterDTO;
import com.OnlineShoppingApp.DTO.CustomerUpdateDTO;
import com.OnlineShoppingApp.DTO.UpdatePasswordDTO;
import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Exception.CustomerException;

import java.util.List;

public interface CustomerService {

    public Customer addCustomer(CustomerRegisterDTO customerRegisterDTO)throws CustomerException;

    public Customer viewCustomerById(Integer customerId) throws CustomerException;

    public Customer updateCustomer(CustomerUpdateDTO customerUpdtDto, String key)throws CustomerException;

    public Customer deleteCustomerById(Integer customerId) throws CustomerException;

    public List<Customer> viewAllConsumer() throws CustomerException;
    
    public String updatePassword(UpdatePasswordDTO dto) throws CustomerException;

}
