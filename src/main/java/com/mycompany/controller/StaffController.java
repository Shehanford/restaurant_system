package com.mycompany.controller;

import com.mycompany.ExceptionHandler.UserNotFoundException;
import com.mycompany.model.Staff;
import com.mycompany.service.StaffService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.mycompany.ExceptionHandler.IncorrectPasswordException;
import org.springframework.web.servlet.ModelAndView;
import com.mycompany.repository.StaffRepository;


import java.util.List;

@Controller
public class StaffController {

    @Autowired
    private StaffService service;

    @Autowired
    private StaffRepository staffRepo;

    @GetMapping("/staffHome")
    public String showStaffHome() {
        return "staffHome";
    }

    @GetMapping("/staffRegister")
    public String showRegistrationForm(Model model) {
        Staff staff = new Staff();
        model.addAttribute("staff", new Staff());
        return "staffRegister";
    }

    @PostMapping("/staffRegister/save")
    public String saveStaff(Staff staff) {
        String hashedPassword = BCrypt.hashpw(staff.getPassword(), BCrypt.gensalt(12));
        staff.setPassword(hashedPassword);
        service.save(staff);
        return "redirect:/staffList";
    }

    @GetMapping("/staffList")
    public ModelAndView getAllStaff(){
        List<Staff> listStaffs = service.getAllStaff();
        return new ModelAndView("staffList","staff", listStaffs);
    }

    @RequestMapping("/deleteStaffList/{id}")
    public String deleteStaffList(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:/staffList";
    }

    @RequestMapping("/editStaff/{id}")
    public String editStaff(@PathVariable("id") int id, Model model){
        Staff staff = service.getStaffById(id);
        model.addAttribute("staff", staff);
        return "staffEdit";
    }

    @GetMapping("/staffLogin")
    public String showLoginForm(Staff staff) {
        return "staffLogin";
    }

    @PostMapping("/staffLoginpage")
    public String loginProcess(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        try {
            Staff dbStaff = service.findByEmail(email);
            if (dbStaff == null) {
                throw new UserNotFoundException();
            }

            boolean isPasswordMatch = BCrypt.checkpw(password, dbStaff.getPassword());
            if (isPasswordMatch) {
                return "redirect:/staffHome";
            } else {
                throw new IncorrectPasswordException();
            }
        } catch (UserNotFoundException | IncorrectPasswordException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "staffLogin";
        }
    }
}
