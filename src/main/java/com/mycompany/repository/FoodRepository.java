package com.mycompany.repository;

import com.mycompany.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FoodRepository extends JpaRepository<Food,Integer>{

}
