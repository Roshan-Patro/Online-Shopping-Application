package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.Repository.*;
import com.OnlineShoppingApp.Entity.CurrentSession;
import com.OnlineShoppingApp.DTO.UserDTO;
import com.OnlineShoppingApp.Entity.Customer;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CustomerLoginLogoutImpl implements CustomerLoginLogout {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private SessionDao sessionDao;

    @Override
    public String customerLogin(UserDTO dto) throws LoginException {

        Customer existCustomer= customerDao.findByEmail(dto.getEmail());
        if(existCustomer==null){
            throw new LoginException("Customer not Login.....");
        }
        Optional<CurrentSession> validSessionOpt= sessionDao.findById(existCustomer.getCustomerId());
        if(validSessionOpt.isPresent()) {
            throw new LoginException("Customer already Logged In");
        }
        if(existCustomer.getPassword().equals(dto.getPassword())) {
            String ConsumerOTP= RandomString.make(4);

            CurrentSession currentSession = new CurrentSession(existCustomer.getCustomerId(),"CONSUMER",ConsumerOTP, LocalDateTime.now());

            sessionDao.save(currentSession);

            return currentSession.toString();
        }
        else{
            throw new LoginException("Please Enter a valid password");
        }
    }

    @Override
    public String customerLogout(Integer id) throws LoginException {
        CurrentSession validConsumerSession = sessionDao.findById(id).orElseThrow(()-> new LoginException("User Not Logged ...."));

        if(validConsumerSession.getType().equalsIgnoreCase("CONSUMER")){
            sessionDao.delete(validConsumerSession);
            return "Logged Out !";
        }else
            throw new LoginException("Please enter Valid Customer ID");

    }
}
