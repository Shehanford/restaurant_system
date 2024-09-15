package com.mycompany.controller;

import com.mycompany.model.UserInquiry;
import com.mycompany.service.UserInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserInquiryController {

    private final UserInquiryService userInquiryService;

    @Autowired
    public UserInquiryController(UserInquiryService userInquiryService) {
        this.userInquiryService = userInquiryService;
    }

    @GetMapping("/userInquiry")
    public String showInquiryForm(Model model) {
        model.addAttribute("userInquiry", new UserInquiry());
        return "userInquiry";
    }

    @PostMapping("/userInquiry/save")
    public String saveUserInquiry(@ModelAttribute UserInquiry userInquiry, RedirectAttributes redirectAttributes) {
        try {
            userInquiryService.saveUserInquiry(userInquiry);
            redirectAttributes.addFlashAttribute("successMessage", "Your inquiry has been submitted successfully!");
            return "redirect:/userInquiry";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while submitting your inquiry.");
            return "redirect:/userInquiry";
        }
    }
}