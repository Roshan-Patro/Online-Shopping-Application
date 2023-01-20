package com.OnlineShoppingApp.Controller;

import com.OnlineShoppingApp.DTO.AdminLogInDTO;
import com.OnlineShoppingApp.Exception.LoginLogoutException;
import com.OnlineShoppingApp.Service.AdminLoginLogoutService;
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
    private AdminLoginLogoutService adminLoginLogoutService;

    @PostMapping("/admin/login")
    public ResponseEntity<String> logInAdmin(@RequestBody AdminLogInDTO dto) throws LoginLogoutException {
        String result = adminLoginLogoutService.logIntoAccount(dto);
        return new ResponseEntity<String>(result, HttpStatus.OK );
    }

    @PostMapping("/admin/logout")
    public String logoutAdmin(@RequestParam("id") Integer id, @RequestParam("key") String key) throws LoginLogoutException {
        return adminLoginLogoutService.logOutFromAccount(id, key);
    }
}
