package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.DAO.AdminDao;
import com.OnlineShoppingApp.Entity.Admin;
import com.OnlineShoppingApp.Exception.AdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin addAdmin(Admin admin) throws AdminException {
        Admin extAdmin= adminDao.findByAdminCompanyId(admin.getAdminCompanyId());
        if(extAdmin!=null)
            throw new AdminException("Admin Already Exist");

        return adminDao.save(admin);
    }

    @Override
    public Admin updateAdmin(Admin admin) throws AdminException {
        Optional<Admin>opt=adminDao.findById(admin.getAdminLoginId());
        if(opt==null){
            throw new AdminException("Admin Not Found by Id");
        }
        return adminDao.save(admin);
    }

    @Override
    public Admin getAdminById(Integer adminId) throws AdminException {
        Admin opt=adminDao.findById(adminId).orElseThrow(()->new AdminException("Admin Not Found by Id"));
        return opt;
    }

    @Override
    public Admin deleteAdminById(Integer adminId) throws AdminException {
        Optional<Admin>opt=adminDao.findById(adminId);
        if(opt!=null){
            Admin extAdmin=opt.get();
            adminDao.delete(extAdmin);
            return extAdmin;
        }else {
            throw new AdminException("Admin Not Found by Id");
        }
    }

    @Override
    public List<Admin> allAdmin() throws AdminException {
        List<Admin> adminList=adminDao.findAll();
        if (adminList==null){
            throw new AdminException("Admin Not Register....");
        }
        return adminList;
    }


}
