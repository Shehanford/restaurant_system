package com.mycompany.service;

import com.mycompany.model.Food;
import com.mycompany.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepo;

    public void save(Food food){
        foodRepo.save(food);
    }

    public List<Food> getAllFood(){
        return foodRepo.findAll();
    }

    public void deleteById(int id){
        foodRepo.deleteById(id);
    }

    public Food getFoodById(int id){
        return foodRepo.findById(id).get();
    }

}
