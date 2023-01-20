package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.DTO.AdminRegisterDTO;
import com.OnlineShoppingApp.DTO.AdminUpdateDTO;
import com.OnlineShoppingApp.DTO.UpdatePasswordDTO;
import com.OnlineShoppingApp.Entity.Admin;
import com.OnlineShoppingApp.Exception.AdminException;
import com.OnlineShoppingApp.Exception.CustomerException;

import java.util.List;

public interface AdminService {

    public Admin addAdmin(AdminRegisterDTO adminRegDto)throws AdminException;

    public Admin updateAdmin(AdminUpdateDTO adminUpdtDto, String key)throws AdminException;

    public Admin getAdminById(Integer adminId) throws AdminException;

    public Admin deleteAdminById(Integer adminId) throws AdminException;

    public List<Admin>allAdmin()throws AdminException;
    
    public String updatePassword(UpdatePasswordDTO dto) throws AdminException;
}
