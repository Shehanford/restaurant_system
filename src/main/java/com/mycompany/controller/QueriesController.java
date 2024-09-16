package com.mycompany.controller;



import com.mycompany.model.Queries;
import com.mycompany.repository.QueriesRepository;
import com.mycompany.service.QueriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class QueriesController {

    @Autowired
    private QueriesService service;

    @GetMapping("/queries")
    public String showQueriesForm(Model model){
        Queries queries = new Queries();
        model.addAttribute("queries", new Queries());
        return "queries";
    }

    @PostMapping("/queries/save")
    public String saveQueries(@ModelAttribute Queries queries){
        service.save(queries);
        return "redirect:/userHome";
    }
    @GetMapping("/queriesList")
    public ModelAndView getAllQueries(){
        List<Queries> listQueries= service.getAllQueries();
        return new ModelAndView("queriesList","queries", listQueries);
    }

    @RequestMapping("/deleteQueriesList/{id}")
    public String deleteQueriesList(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:/queriesList";
    }

    @RequestMapping("/editQueriesList/{id}")
    public String editQueries(@PathVariable("id") int id, Model model){
        Queries queries = service.getQueriesById(id);
        model.addAttribute("queries", queries);
        return "QueriesEdit";
    }


}


