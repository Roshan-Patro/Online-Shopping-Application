package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.Entity.Admin;
import com.OnlineShoppingApp.Exception.AdminException;

import java.util.List;

public interface AdminService {

    public Admin addAdmin(Admin admin)throws AdminException;

    public Admin updateAdmin(Admin admin)throws AdminException;

    public Admin getAdminById(Integer adminId) throws AdminException;

    public Admin deleteAdminById(Integer adminId) throws AdminException;

    public List<Admin>allAdmin()throws AdminException;
}
