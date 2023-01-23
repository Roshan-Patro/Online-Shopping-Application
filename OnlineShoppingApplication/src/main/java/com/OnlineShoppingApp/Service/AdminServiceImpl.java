package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.Repository.*;
import com.OnlineShoppingApp.DTO.AdminRegisterDTO;
import com.OnlineShoppingApp.DTO.AdminUpdateDTO;
import com.OnlineShoppingApp.DTO.UpdatePasswordDTO;
import com.OnlineShoppingApp.Entity.Admin;
import com.OnlineShoppingApp.Entity.CurrentSession;
import com.OnlineShoppingApp.Entity.User;
import com.OnlineShoppingApp.Enum.Role;
import com.OnlineShoppingApp.Exception.AdminException;
import com.OnlineShoppingApp.Exception.CustomerException;
import com.OnlineShoppingApp.Exception.LoginLogoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminDao adminDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private SessionDao sDao;

    @Override
    public Admin addAdmin(AdminRegisterDTO adminRegDto) throws AdminException {
        Admin extAdmin= adminDao.findByAdminCompanyId(adminRegDto.getAdminCompanyId());
        if(extAdmin!=null)
            throw new AdminException("Admin Already Exists");
        
        Admin admin = new Admin();
        admin.setAdminCompanyId(adminRegDto.getAdminCompanyId());
        admin.setAdminName(adminRegDto.getAdminName());
        Admin savedAdmin = adminDao.save(admin);
        
        User user = new User(savedAdmin.getAdminLoginId(),savedAdmin.getAdminCompanyId(),adminRegDto.getPassword(),Role.ADMIN);
        userDao.save(user);
        
        return savedAdmin;
    }

    @Override
    public Admin updateAdmin(AdminUpdateDTO adminUpdtDto, String key) throws AdminException {
        Optional<Admin> opt = adminDao.findById(adminUpdtDto.getAdminLoginId());
        if(opt.isPresent()==false){
            throw new AdminException("Admin Not Found..!");
        }
        
        CurrentSession validCurrentSession = sDao.findByUuid(key);
        if(validCurrentSession!=null && validCurrentSession.getId()==adminUpdtDto.getAdminLoginId()) {
        	Admin admin = opt.get();
            admin.setAdminCompanyId(adminUpdtDto.getAdminCompanyId());
            admin.setAdminName(adminUpdtDto.getAdminName());
            
            Admin updatedAdmin = adminDao.save(admin);
            
            Optional<User> optUser = userDao.findById(updatedAdmin.getAdminLoginId());
            User existingUser = optUser.get();
            existingUser.setCompIdEmail(updatedAdmin.getAdminCompanyId());
            userDao.save(existingUser);
            
            return updatedAdmin;
        }
        throw new AdminException("Either admin not logged in or giving wrong key.");
        
        
    }

    @Override
    public Admin getAdminById(Integer adminId) throws AdminException {
        Optional<Admin> optAdmin = adminDao.findById(adminId);
        
        if(optAdmin.isPresent()) {
        	return optAdmin.get();
        }
        throw new AdminException("Admin Not Found by Id");
    }

    @Override
    public Admin deleteAdminById(Integer adminId) throws AdminException {
        Optional<Admin> opt = adminDao.findById(adminId);
        if(opt.isPresent()){
            Admin extAdmin = opt.get();
            adminDao.delete(extAdmin);
            
            Optional<User> optUser = userDao.findById(adminId);
            User existingUser = optUser.get();
            userDao.delete(existingUser);
            
            Optional<CurrentSession> sessionOpt = sDao.findById(adminId);
            if(sessionOpt.isPresent()) {
            	CurrentSession sessionObj = sessionOpt.get();
            	sDao.delete(sessionObj);
            }
            
            return extAdmin;
        }else {
            throw new AdminException("Admin Not Found by Id");
        }
    }

    @Override
    public List<Admin> allAdmin() throws AdminException {
        List<Admin> adminList = adminDao.findAll();
        if (adminList.size()==0){
            throw new AdminException("Admin Not Register....");
        }
        return adminList;
    }

	@Override
	public String updatePassword(UpdatePasswordDTO dto) throws AdminException {
			List<User> userList = userDao.findBycompIdEmailAndpassword(dto.getCompIdEmail(), dto.getOldPassword());
			if(userList.size()!=0) {
				System.out.println("1");
				userList.get(0).setPassword(dto.getNewPassword());
				userDao.save(userList.get(0));
				return "Password updated successfully";
			}
			System.out.println("1");
			throw new CustomerException("Invalid details provided. Please, try again.");
	}


}
