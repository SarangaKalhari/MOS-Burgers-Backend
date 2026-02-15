package org.example.controller;

import org.example.model.entity.Beverages;
import org.example.model.entity.Burger;
import org.example.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("burger")
@CrossOrigin("http://localhost:4200/")
public class BurgerController {

    @Autowired
    BurgerService burgerService;

    @GetMapping("/all")
    public List<Burger> getBurgers(){
        return burgerService.getAllBurgers();
    }

    @GetMapping("/all/{categories}")
    public List<Burger> categorizedItems(@PathVariable String categories){
        return burgerService.getCategories(categories);
    }

    @GetMapping("/categories")
    public List<String> getCategories(){
        return burgerService.getAllCategories();
    }
}
