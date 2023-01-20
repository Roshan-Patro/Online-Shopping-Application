package com.OnlineShoppingApp.Controller;

import com.OnlineShoppingApp.DTO.AdminLogInDTO;
import com.OnlineShoppingApp.DTO.UserDTO;
import com.OnlineShoppingApp.Service.CustomerLoginLogout;
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
    private CustomerLoginLogout customerLoginLogout;

    @PostMapping("/customer/login")
    public ResponseEntity<String> logInCustomer(@RequestBody UserDTO dto) throws LoginException {
        String result = customerLoginLogout.customerLogin(dto);
        return new ResponseEntity<String>(result, HttpStatus.OK );
    }

    @PostMapping("/customer/logout")
    public String logoutAdmin(@RequestParam(required = false) Integer id) throws LoginException {
        return customerLoginLogout.customerLogout(id);
    }
}
