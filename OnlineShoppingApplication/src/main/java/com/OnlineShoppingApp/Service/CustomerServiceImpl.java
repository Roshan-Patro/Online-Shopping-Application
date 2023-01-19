package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.Repository.*;
import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao cDao;

    @Override
    public Customer addCustomer(Customer customer) throws CustomerException {
        Customer existCustomer= cDao.findByEmail(customer.getEmail());

        if(existCustomer!=null){
            throw new CustomerException("Customer Already Exist");
        }
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setEmail(customer.getEmail());
        customer.setPassword(customer.getPassword());

        Customer newCustomer= cDao.save(customer);
        return newCustomer;
    }

    @Override
    public Customer viewCustomer(Integer Customer_id) throws CustomerException {
        Optional<Customer>optionalCustomer= cDao.findById(Customer_id);


        if(optionalCustomer==null){
            throw new CustomerException("Customer Not Register Exist");
        }else{
            Customer extCustomer=optionalCustomer.get();
            return extCustomer;
        }
    }

    @Override
    public Customer updateCustomer(Customer customer, Integer Customer_id) throws CustomerException {
        Optional<Customer>optionalCustomer= cDao.findById(Customer_id);


        if(optionalCustomer==null){
            throw new CustomerException("Customer Not Register Exist");
        }else{
            Customer extCustomer=optionalCustomer.get();
            extCustomer.setFirstName(customer.getFirstName());
            extCustomer.setLastName(customer.getLastName());
            extCustomer.setMobileNumber(customer.getMobileNumber());
            extCustomer.setEmail(customer.getEmail());
            extCustomer.setPassword(customer.getPassword());

            Customer updatedCustomer=cDao.save(extCustomer);
            return updatedCustomer;
        }
    }

    @Override
    public Customer deleteCustomer(Integer Customer_id) throws CustomerException {
        Optional<Customer>optionalCustomer= cDao.findById(Customer_id);
         if(optionalCustomer==null){
            throw new CustomerException("Customer Not Register Exist");
        }else{
            Customer extCustomer=optionalCustomer.get();
            cDao.delete(extCustomer);
            return extCustomer;
        }
    }

    @Override
    public List<Customer> viewAllConsumer() throws CustomerException {
        List<Customer> customerList= cDao.findAll();
        if(customerList==null){
            throw new CustomerException("Customer are Not Registered");
        }
        return customerList;
    }
}
