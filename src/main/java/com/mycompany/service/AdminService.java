package com.mycompany.service;

import com.mycompany.model.Admin;
import com.mycompany.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    public void save(Admin admin) {
        adminRepo.save(admin);
    }

    public List<Admin> getAllAdmin(){
        return adminRepo.findAll();
    }

    public void deleteById(int id) {
        adminRepo.deleteById(id);
    }

    public Admin getAdminById(int id){
        return adminRepo.findById(id).get();
    }
    public Admin findByEmail(String email){
        Admin admin = adminRepo.findByEmail(email);
        return admin;
    }
}