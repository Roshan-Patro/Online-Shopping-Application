package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.DTO.CustomerLogInDTO;
import com.OnlineShoppingApp.Exception.LoginLogoutException;

import javax.security.auth.login.LoginException;

public interface CustomerLoginLogoutService {

    public String customerLogin(CustomerLogInDTO dto) throws LoginLogoutException;

    public String customerLogout(Integer userId, String key) throws LoginLogoutException;
}
