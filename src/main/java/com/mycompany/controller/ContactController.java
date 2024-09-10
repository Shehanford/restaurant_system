package com.mycompany.controller;


import com.mycompany.model.Contact;
import com.mycompany.repository.ContactRepository;
import com.mycompany.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String handleContactForm(@ModelAttribute("contact") Contact contact) {
        contactService.saveContact(contact);
        return "redirect:/contact?success";
    }
}