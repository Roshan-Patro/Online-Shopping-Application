package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.DTO.UserDTO;

import javax.security.auth.login.LoginException;

public interface CustomerLoginLogout {

    public String customerLogin(UserDTO dto)throws LoginException;

    public String customerLogout(Integer id)throws LoginException;
}
