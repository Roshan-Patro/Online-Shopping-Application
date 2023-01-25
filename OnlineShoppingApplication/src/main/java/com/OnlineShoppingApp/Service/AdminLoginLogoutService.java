package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.DTO.AdminLogInDTO;
import com.OnlineShoppingApp.Exception.LoginLogoutException;


public interface AdminLoginLogoutService {

	// Admin login
    public String logIntoAccount(AdminLogInDTO adminLoginDto) throws LoginLogoutException;

    // Admin logout
    public String logOutFromAccount(Integer adminId, String adminKey) throws LoginLogoutException;
}
