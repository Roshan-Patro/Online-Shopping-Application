package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.DTO.AdminDTO;

import javax.security.auth.login.LoginException;

public interface AdminLoginLogout {

    public String logIntoAccount(AdminDTO dto)throws LoginException;

    public String logOutFromAccount(Integer id)throws LoginException;
}
