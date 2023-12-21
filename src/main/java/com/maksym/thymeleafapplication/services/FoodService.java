package com.maksym.thymeleafapplication.services;

import com.maksym.thymeleafapplication.mappers.FoodMapper;
import com.maksym.thymeleafapplication.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    private FoodMapper foodMapper;
    public Food[] getFood(){
        return foodMapper.selectedFood();
    }

    public int saveFood(Food food){
        food.setId(foodMapper.getNextId());

        Food[] check = foodMapper.ifExists(food);
        if(check.length==0) return foodMapper.saveFood(food);
        foodMapper.updatePrice(food);
        return -1;


    }
}
