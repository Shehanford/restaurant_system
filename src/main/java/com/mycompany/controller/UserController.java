package com.mycompany.controller;

import com.mycompany.model.User;
import com.mycompany.repository.UserRepository;
import com.mycompany.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.mycompany.ExceptionHandler.IncorrectPasswordException;
import com.mycompany.ExceptionHandler.UserNotFoundException;
import java.util.List;


@SuppressWarnings("unused")
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepo;


    @GetMapping("/userHome")
    public String showUserHome() {
        return "userHome";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/save")
    public String saveUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hashedPassword);
        service.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(User user) {
        return "login";
    }

    @PostMapping("/loginpage")
    public String loginProcess(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        try {
            User dbUser = service.findByEmail(email);
            if (dbUser == null) {
                throw new UserNotFoundException();
            }

            boolean isPasswordMatch = BCrypt.checkpw(password, dbUser.getPassword());
            if (isPasswordMatch) {
                return "redirect:/userHome";
            } else {
                throw new IncorrectPasswordException();
            }
        } catch (UserNotFoundException | IncorrectPasswordException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/userHistory")
    public ModelAndView getAllUsers() {
        List<User> list = service.getAllUsers();
        return new ModelAndView("userHistory", "users", list);
    }

    @RequestMapping("/deleteUserList/{id}")
    public String deleteUserList(@PathVariable("id") int id) {
        service.deleteById(id);
        return "redirect:/userHistory";
    }


}