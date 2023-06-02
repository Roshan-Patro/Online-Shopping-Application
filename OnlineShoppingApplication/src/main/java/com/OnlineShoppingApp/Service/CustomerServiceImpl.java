package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.Repository.*;
import com.OnlineShoppingApp.DTO.CustomerRegisterDTO;
import com.OnlineShoppingApp.DTO.CustomerUpdateDTO;
import com.OnlineShoppingApp.DTO.UpdatePasswordDTO;
import com.OnlineShoppingApp.Entity.Cart;
import com.OnlineShoppingApp.Entity.CurrentSession;
import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Entity.User;
import com.OnlineShoppingApp.Enum.Role;
import com.OnlineShoppingApp.Exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao cDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private SessionDao sDao;

    @Override
    public Customer addCustomer(@Valid CustomerRegisterDTO customerRegisterDTO) throws CustomerException {
        Customer existCustomer= cDao.findByEmail(customerRegisterDTO.getEmail());

        if(existCustomer!=null){
            throw new CustomerException("Customer Already Exist");
        }
        
        Customer customer = new Customer();
        customer.setFirstName(customerRegisterDTO.getFirstName());
        customer.setLastName(customerRegisterDTO.getLastName());
        customer.setEmail(customerRegisterDTO.getEmail());
        customer.setMobileNumber(customerRegisterDTO.getMobileNumber());
        
        Cart cart = new Cart();
        cart.setCustomer(customer);
        
        customer.setCart(cart);
        
        Customer newCustomer= cDao.save(customer);
        
        User user = new User(newCustomer.getCustomerId(),newCustomer.getEmail(),customerRegisterDTO.getPassword(),Role.CUSTOMER);
        userDao.save(user);
        
        return newCustomer;
    }

    @Override
    public Customer viewCustomerById(Integer customerId) throws CustomerException {
        Optional<Customer> optionalCustomer = cDao.findById(customerId);


        if(optionalCustomer.isPresent()==false){
            throw new CustomerException("Customer Not Found");
        }else{
            Customer extCustomer=optionalCustomer.get();
            return extCustomer;
        }
    }

    @Override
    public Customer updateCustomer(CustomerUpdateDTO customerUpdtDto, String key) throws CustomerException {
        Optional<Customer> optionalCustomer = cDao.findById(customerUpdtDto.getCustomerId());


        if(optionalCustomer.isPresent()==false){
            throw new CustomerException("Customer Not Found");
        }
        
        CurrentSession validCurrentSession = sDao.findByUuid(key);
        
        if(validCurrentSession!=null && validCurrentSession.getId()==customerUpdtDto.getCustomerId()) {
        	Customer extCustomer = optionalCustomer.get();
            extCustomer.setFirstName(customerUpdtDto.getFirstName());
            extCustomer.setLastName(customerUpdtDto.getLastName());
            extCustomer.setMobileNumber(customerUpdtDto.getMobileNumber());
            extCustomer.setEmail(customerUpdtDto.getEmail());

            Customer updatedCustomer=cDao.save(extCustomer);
            
            Optional<User> optUser = userDao.findById(updatedCustomer.getCustomerId());
            User existingUser = optUser.get();
            existingUser.setCompIdEmail(updatedCustomer.getEmail());
            userDao.save(existingUser);
            
            return updatedCustomer;
        }
        
        throw new CustomerException("Either customer not logged in or giving wrong key.");
            
        
    }

    @Override
    public Customer deleteCustomerById(Integer customerId) throws CustomerException {
        Optional<Customer> optionalCustomer = cDao.findById(customerId);
         if(optionalCustomer.isPresent()==false){
            throw new CustomerException("Customer Not Found");
        }else{
            Customer extCustomer = optionalCustomer.get();
            cDao.delete(extCustomer);
            
            Optional<User> optUser = userDao.findById(customerId);
            User existingUser = optUser.get();
            userDao.delete(existingUser);
            
            Optional<CurrentSession> sessionOpt = sDao.findById(customerId);
            if(sessionOpt.isPresent()) {
            	CurrentSession sessionObj = sessionOpt.get();
            	sDao.delete(sessionObj);
            }
            
            return extCustomer;
        }
    }

    @Override
    public List<Customer> viewAllConsumer() throws CustomerException {
        List<Customer> customerList= cDao.findAll();
        if(customerList.size()==0){
            throw new CustomerException("Customer are Not Registered");
        }
        return customerList;
    }

	@Override
	public String updatePassword(UpdatePasswordDTO dto) throws CustomerException {
		List<User> userList = userDao.findBycompIdEmailAndpassword(dto.getCompIdEmail(), dto.getOldPassword());
		if(userList.size()!=0) {
			userList.get(0).setPassword(dto.getNewPassword());
			userDao.save(userList.get(0));
			return "Password updated successfully";
		}
		throw new CustomerException("Invalid details provided. Please, try again.");
	}
}
