package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Exception.CustomerException;

import java.util.List;

public interface CustomerService {

    public Customer addCustomer(Customer customer)throws CustomerException;

    public Customer viewCustomer(Integer Customer_id) throws CustomerException;

    public Customer updateCustomer(Customer customer, Integer Customer_id)throws CustomerException;

    public Customer deleteCustomer(Integer Customer_id) throws CustomerException;

    public List<Customer> viewAllConsumer() throws CustomerException;

}
