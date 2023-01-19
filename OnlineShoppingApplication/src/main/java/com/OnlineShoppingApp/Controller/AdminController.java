package com.OnlineShoppingApp.Controller;

import com.OnlineShoppingApp.Entity.Admin;
import com.OnlineShoppingApp.Exception.AdminException;
import com.OnlineShoppingApp.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin")
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) throws AdminException {

        Admin savedAdmin = adminService.addAdmin(admin);

        return new ResponseEntity<Admin>(savedAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/adminUpdate")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) throws AdminException {

        Admin updateAdmin = adminService.updateAdmin(admin);

        return new ResponseEntity<Admin>(updateAdmin, HttpStatus.CREATED);
    }

    @GetMapping("/getAdminById/{adminId}")
    public ResponseEntity<Admin> getByAdminId(@PathVariable Integer adminId) throws AdminException {

        Admin getByAdminId = adminService.getAdminById(adminId);

        return new ResponseEntity<Admin>(getByAdminId, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteAdminById/{adminId}")
    public ResponseEntity<Admin> deleteByAdminId(@PathVariable Integer adminId) throws AdminException {

        Admin deleteByAdminId = adminService.deleteAdminById(adminId);

        return new ResponseEntity<Admin>(deleteByAdminId, HttpStatus.CREATED);
    }

    @GetMapping("/getAdminList")
    public ResponseEntity<List<Admin>> getListAdmin() throws AdminException {

        List<Admin> getListAdmin = adminService.allAdmin();

        return new ResponseEntity<List<Admin>>(getListAdmin, HttpStatus.CREATED);
    }

}
