package com.OnlineShoppingApp.Controller;

import com.OnlineShoppingApp.DTO.AdminLogInDTO;
import com.OnlineShoppingApp.Exception.LoginLogoutException;
import com.OnlineShoppingApp.Service.AdminLoginLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminLoginLogoutController {

	// Injecting dependencies
    @Autowired
    private AdminLoginLogoutService adminLoginLogoutService;

    
    // Creating APIs
    
    // Admin login
    @PostMapping("/login")
    public ResponseEntity<String> logInAdmin(@RequestBody AdminLogInDTO adminLoginDto) throws LoginLogoutException {
        String result = adminLoginLogoutService.logIntoAccount(adminLoginDto);
        return new ResponseEntity<String>(result, HttpStatus.OK );
    }

    // Admin logout
    @PostMapping("/logout")
    public String logoutAdmin(@RequestParam("adminId") Integer adminId, @RequestParam("adminKey") String adminKey) throws LoginLogoutException {
        return adminLoginLogoutService.logOutFromAccount(adminId, adminKey);
    }
}
