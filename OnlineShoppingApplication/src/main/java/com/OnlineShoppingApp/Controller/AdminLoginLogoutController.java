package com.OnlineShoppingApp.Controller;

import com.OnlineShoppingApp.DTO.AdminDTO;
import com.OnlineShoppingApp.Service.AdminLoginLogout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

@RestController
public class AdminLoginLogoutController {

    @Autowired
    private AdminLoginLogout adminLoginLogout;

    @PostMapping("/admin/login")
    public ResponseEntity<String> logInAdmin(@RequestBody AdminDTO dto) throws LoginException {
        String result = adminLoginLogout.logIntoAccount(dto);

        return new ResponseEntity<String>(result, HttpStatus.OK );
    }

    @PostMapping("/admin/logout")
    public String logoutAdmin(@RequestParam(required = false) String adminOtp) throws LoginException {
        return adminLoginLogout.logOutFromAccount(adminOtp);

    }
}
