package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.Repository.*;
import com.OnlineShoppingApp.DTO.AdminLogInDTO;
import com.OnlineShoppingApp.Entity.CurrentSession;
import com.OnlineShoppingApp.Entity.User;
import com.OnlineShoppingApp.Enum.Role;
import com.OnlineShoppingApp.Exception.LoginLogoutException;
import com.OnlineShoppingApp.Entity.Admin;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminLoginLogoutServiceImpl implements AdminLoginLogoutService{

	// Injecting dependencies
    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private AdminDao adminDao;
    
    @Autowired
    private UserDao userDao;

    
    // Overriding parent abstract methods
    
    // Admin login
    @Override
    public String logIntoAccount(AdminLogInDTO adminLoginDto) throws LoginLogoutException {
        Admin existingAdmin= adminDao.findByAdminCompanyId(adminLoginDto.getAdminCompanyId());

        if(existingAdmin==null){
            throw new LoginLogoutException("Admin doesn't exist with company id: "+adminLoginDto.getAdminCompanyId());
        }

        Optional<CurrentSession> validSessionOpt = sessionDao.findById(existingAdmin.getAdminLoginId());
        if(validSessionOpt.isPresent()) {
            throw new LoginLogoutException("Admin already logged In with login id: "+existingAdmin.getAdminLoginId());
        }
        User existingUser = userDao.findByCompIdEmail(adminLoginDto.getAdminCompanyId());
        if(existingUser.getPassword().equals(adminLoginDto.getPassword())) {

            String adminOTP = RandomString.make(4);

            CurrentSession currentSession = new CurrentSession(existingAdmin.getAdminLoginId(), Role.ADMIN, adminOTP, LocalDateTime.now());

            sessionDao.save(currentSession);

            return currentSession.toString();
        }
        else
            throw new LoginLogoutException("Please Enter a valid password");
    }

    // Admin logout
    @Override
    public String logOutFromAccount(Integer adminId, String adminKey) throws LoginLogoutException {
        CurrentSession validSession = sessionDao.findByUuid(adminKey);
        
        if(validSession==null) {
        	throw new LoginLogoutException("Invalid key: "+adminKey);
        }

        if(validSession.getRole().toString().equalsIgnoreCase(Role.ADMIN.toString()) && validSession.getId()==adminId){
            sessionDao.delete(validSession);
            return "Logged Out!";
        }else
            throw new LoginLogoutException("Invalid admin id: "+adminId+" or admin has not logged in yet.");
    }
}
