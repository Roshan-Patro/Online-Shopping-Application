package com.OnlineShoppingApp.Controller;

import com.OnlineShoppingApp.DTO.AdminLogInDTO;
import com.OnlineShoppingApp.DTO.CustomerLogInDTO;
import com.OnlineShoppingApp.Service.CustomerLoginLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

@RestController
public class CustomerLoginLogoutController {

    @Autowired
    private CustomerLoginLogoutService customerLoginLogout;

    @PostMapping("/customer/login")
    public ResponseEntity<String> logInCustomer(@RequestBody CustomerLogInDTO dto){
        String result = customerLoginLogout.customerLogin(dto);
        return new ResponseEntity<String>(result, HttpStatus.OK );
    }

    @PostMapping("/customer/logout")
    public String logoutAdmin(@RequestParam(required = false) Integer id, String key){
        return customerLoginLogout.customerLogout(id, key);
    }
}
