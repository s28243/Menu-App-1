package com.maksym.thymeleafapplication.conrollers;

import com.maksym.thymeleafapplication.model.Food;
import com.maksym.thymeleafapplication.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/")
    public String home(Model model, Food food){

        model.addAttribute("message", "Hello from app");

        model.addAttribute("foods", foodService.getFood());

        return "home";
    }

    @PostMapping("/")
    public String postHome(Model model, Food food){
        if(food.getPrice()>200){
            model.addAttribute("errorMessage",
                    String.format("Could not add the %s, too expensive!", food.getName()));
            }else{
            int save = foodService.saveFood(food);
            if(save==-1) {
                model.addAttribute("updateMessage",
                        String.format("The price for the %s has been updated!", food.getName()));
            }else if(save<1) {
                model.addAttribute("errorMessage",
                        String.format("Could not add the %s, try again!", food.getName()));
            }else model.addAttribute("successfulMessage",
                    String.format("The %s added to the menu!", food.getName()));
        }
        Food[] foods = foodService.getFood();
        model.addAttribute("foods", foods);
        return "home";
    }
}
