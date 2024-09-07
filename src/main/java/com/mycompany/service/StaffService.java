package com.mycompany.service;

import com.mycompany.model.Staff;
import com.mycompany.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepo;

    public void save(Staff staff) {
        staffRepo.save(staff);
    }

    public List<Staff> getAllStaff(){
        return staffRepo.findAll();
    }

    public void deleteById(int id) {
        staffRepo.deleteById(id);
    }

    public Staff getStaffById(int id){
        return staffRepo.findById(id).get();
    }
    public Staff findByEmail(String email){
        Staff staff = staffRepo.findByEmail(email);
        return staff;
    }
}
