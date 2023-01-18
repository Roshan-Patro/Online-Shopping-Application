package com.OnlineShoppingApp.Service;

import com.OnlineShoppingApp.DAO.AdminDao;
import com.OnlineShoppingApp.DAO.SessionDao;
import com.OnlineShoppingApp.DTO.AdminDTO;
import com.OnlineShoppingApp.DTO.CurrentSession;
import com.OnlineShoppingApp.Entity.Admin;
import com.OnlineShoppingApp.Exception.AdminException;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminLoginLogoutImpl implements AdminLoginLogout{

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private AdminDao adminDao;

    @Override
    public String logIntoAccount(AdminDTO dto) throws LoginException {
        Admin existingAdmin= adminDao.findByAdminCompanyId(dto.getAdminCompanyId());

        if(existingAdmin==null){
            throw new LoginException("Admin Not Login...");
        }

        Optional<CurrentSession>validSessionOpt= sessionDao.findById(existingAdmin.getAdminLoginId());
        if(validSessionOpt.isPresent()) {
            throw new LoginException("Admin already Logged In");
        }if(existingAdmin.getPassword().equals(dto.getPassword())) {

            String AdminOTP= RandomString.make(4);

            CurrentSession currentSession = new CurrentSession(existingAdmin.getAdminLoginId(),"ADMIN",AdminOTP, LocalDateTime.now());

            sessionDao.save(currentSession);

            return currentSession.toString();
        }
        else
            throw new LoginException("Please Enter a valid password");
    }

    @Override
    public String logOutFromAccount(String adminOtp) throws LoginException {
        CurrentSession validSession = sessionDao.findByUuid(adminOtp);

        if(validSession == null) {
            throw new LoginException("Admin Not Logged In");

        }
        sessionDao.delete(validSession);
        return "Logged Out !";
    }
}
