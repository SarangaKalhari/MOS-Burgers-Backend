package org.example.controller;

import org.example.model.entity.Burger;
import org.example.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
