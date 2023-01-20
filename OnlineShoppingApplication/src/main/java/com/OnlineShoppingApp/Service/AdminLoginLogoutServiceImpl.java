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

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminLoginLogoutServiceImpl implements AdminLoginLogoutService{

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private AdminDao adminDao;
    
    @Autowired
    private UserDao userDao;

    @Override
    public String logIntoAccount(AdminLogInDTO dto) throws LoginLogoutException {
        Admin existingAdmin= adminDao.findByAdminCompanyId(dto.getAdminCompanyId());

        if(existingAdmin==null){
            throw new LoginLogoutException("Admin doesn't exist...!");
        }

        Optional<CurrentSession> validSessionOpt = sessionDao.findById(existingAdmin.getAdminLoginId());
        if(validSessionOpt.isPresent()) {
            throw new LoginLogoutException("Admin already Logged In");
        }
        User existingUser = userDao.findByCompIdEmail(dto.getAdminCompanyId());
        if(existingUser.getPassword().equals(dto.getPassword())) {

            String AdminOTP= RandomString.make(4);

            CurrentSession currentSession = new CurrentSession(existingAdmin.getAdminLoginId(),Role.ADMIN,AdminOTP, LocalDateTime.now());

            sessionDao.save(currentSession);

            return currentSession.toString();
        }
        else
            throw new LoginLogoutException("Please Enter a valid password");
    }

    @Override
    public String logOutFromAccount(Integer adminId, String key) throws LoginLogoutException {
        CurrentSession validSession = sessionDao.findByUuid(key);
        
        if(validSession==null) {
        	throw new LoginLogoutException("Invalid key");
        }

        if(validSession.getRole().toString().equalsIgnoreCase("ADMIN") && validSession.getId()==adminId){
            sessionDao.delete(validSession);
            return "Logged Out !";
        }else
            throw new LoginLogoutException("Invalid admin id or admin has not logged in.");
    }
}
