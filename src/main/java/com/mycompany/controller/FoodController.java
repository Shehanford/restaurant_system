package com.mycompany.controller;


import com.mycompany.model.Food;
import com.mycompany.repository.FoodRepository;
import com.mycompany.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FoodController {

    @Autowired
    private FoodService service;

    @GetMapping("/food")
    public String showFoodForm(Model model){
        Food food = new Food();
        model.addAttribute("food", new Food());
        return "food";
    }

    @PostMapping("/food/save")
    public String saveFood(@ModelAttribute Food food){
        service.save(food);
        return "redirect:/foodList";
    }

    @GetMapping("/foodList")
    public ModelAndView getAllFood(){
        List<Food> listFood= service.getAllFood();
        return new ModelAndView("foodList","food", listFood);
    }

    @RequestMapping("/deleteFoodList/{id}")
    public String deleteFoodList(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:/foodList";
    }

    @RequestMapping("/editFood/{id}")
    public String editFood(@PathVariable("id") int id, Model model){
        Food food = service.getFoodById(id);
        model.addAttribute("food", food);
        return "foodEdit";
    }
}
