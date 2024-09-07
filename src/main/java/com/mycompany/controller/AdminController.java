package com.mycompany.controller;
import com.mycompany.ExceptionHandler.IncorrectPasswordException;
import com.mycompany.ExceptionHandler.UserNotFoundException;
import com.mycompany.model.Admin;
import com.mycompany.service.AdminService;
import com.mycompany.repository.AdminRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService service;

    @Autowired
    private AdminRepository adminRepo;

    @GetMapping("/adminHome")
    public String showAdminHome() {
        return "adminHome";
    }

    @GetMapping("/adminRegister")
    public String showRegistrationForm(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", new Admin());
        return "adminRegister";
    }

    @PostMapping("/adminRegister/save")
    public String saveAdmin(Admin admin) {
        String hashedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt(12));
        admin.setPassword(hashedPassword);
        service.save(admin);
        return "redirect:/adminList";
    }

    @GetMapping("/adminList")
    public ModelAndView getAllAdmin(){
        List<Admin> listAdmins = service.getAllAdmin();
        return new ModelAndView("adminList","admin", listAdmins);
    }

    @RequestMapping("/deleteAdminList/{id}")
    public String deleteAdminList(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:/adminList";
    }

    @RequestMapping("/editAdmin/{id}")
    public String editAdmin(@PathVariable("id") int id, Model model){
        Admin admin = service.getAdminById(id);
        model.addAttribute("admin", admin);
        return "adminEdit";
    }

    @GetMapping("/adminLogin")
    public String showLoginForm(Admin admin) {
        return "adminLogin";
    }

    @PostMapping("/adminLoginpage")
    public String loginProcess(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        try {
            Admin dbAdmin = service.findByEmail(email);
            if (dbAdmin == null) {
                throw new UserNotFoundException();
            }

            boolean isPasswordMatch = BCrypt.checkpw(password, dbAdmin.getPassword());
            if (isPasswordMatch) {
                return "redirect:/adminHome";
            } else {
                throw new IncorrectPasswordException();
            }
        } catch (UserNotFoundException | IncorrectPasswordException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "adminLogin";
        }
    }
}
