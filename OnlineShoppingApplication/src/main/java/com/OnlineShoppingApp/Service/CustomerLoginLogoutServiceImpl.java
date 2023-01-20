package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.Repository.*;
import com.OnlineShoppingApp.Entity.CurrentSession;
import com.OnlineShoppingApp.DTO.CustomerLogInDTO;
import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Entity.User;
import com.OnlineShoppingApp.Enum.Role;
import com.OnlineShoppingApp.Exception.LoginLogoutException;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CustomerLoginLogoutServiceImpl implements CustomerLoginLogoutService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private SessionDao sessionDao;
    
    @Autowired
    private UserDao userDao;

    @Override
    public String customerLogin(CustomerLogInDTO dto) throws LoginLogoutException {

        Customer existCustomer= customerDao.findByEmail(dto.getEmail());
        if(existCustomer==null){
            throw new LoginLogoutException("Customer doesn't exist..!");
        }
        Optional<CurrentSession> validSessionOpt= sessionDao.findById(existCustomer.getCustomerId());
        if(validSessionOpt.isPresent()) {
            throw new LoginLogoutException("Customer already Logged In");
        }
        User existingUser = userDao.findByCompIdEmail(dto.getEmail());
        if(existingUser.getPassword().equals(dto.getPassword())) {
            String ConsumerOTP= RandomString.make(4);

            CurrentSession currentSession = new CurrentSession(existCustomer.getCustomerId(),Role.CUSTOMER,ConsumerOTP, LocalDateTime.now());

            sessionDao.save(currentSession);

            return currentSession.toString();
        }
        else{
            throw new LoginLogoutException("Please Enter a valid password");
        }
    }

    @Override
    public String customerLogout(Integer userId, String key) throws LoginLogoutException {
    	CurrentSession validSession = sessionDao.findByUuid(key);
    	
    	if(validSession==null) {
        	throw new LoginLogoutException("Invalid key");
        }

        if(validSession.getRole().toString().equalsIgnoreCase("CUSTOMER") && validSession.getId()==userId){
            sessionDao.delete(validSession);
            return "Logged Out !";
        }else
            throw new LoginLogoutException("Invalid customer id or admin has not logged in.");

    }
}
