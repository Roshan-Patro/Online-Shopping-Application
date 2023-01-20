package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.DTO.AdminLogInDTO;
import com.OnlineShoppingApp.Exception.LoginLogoutException;

import javax.security.auth.login.LoginException;

public interface AdminLoginLogoutService {

    public String logIntoAccount(AdminLogInDTO dto)throws LoginLogoutException;

    public String logOutFromAccount(Integer adminId, String key)throws LoginLogoutException;
}
