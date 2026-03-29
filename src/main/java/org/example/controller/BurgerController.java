package org.example.controller;

import org.example.model.dto.BurgerDTO;
import org.example.model.entity.Burger;
import org.example.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("burger")
@CrossOrigin("http://localhost:4200/")
public class BurgerController {

    @Autowired
    BurgerService burgerService;

    @GetMapping
    public List<Burger> getBurgers(){
        return burgerService.getAllBurgers();
    }

    @GetMapping("/{categories}")
    public List<Burger> categorizedItems(@PathVariable String categories){
        return burgerService.getCategories(categories);
    }

    @GetMapping("/categories")
    public List<String> getCategories(){
        return burgerService.getAllCategories();
    }

    @PutMapping("/add")
    public void addBurger(@RequestBody BurgerDTO burgerDTO ){
        burgerService.addBurger(burgerDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBurger(@RequestBody String code){
        burgerService.deleteBurger(code);
        return ResponseEntity.ok("Burger deleted successfully");

    }
}
