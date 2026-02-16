package org.example.controller;

import org.example.model.dto.BeveragesDTO;
import org.example.model.dto.BurgerDTO;
import org.example.model.entity.Beverages;
import org.example.model.entity.Burger;
import org.example.service.BeveragesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("beverages")
@CrossOrigin("http://localhost:4200/")
public class BeveragesController {

    @Autowired
    BeveragesService beveragesService;

    @GetMapping
    public List<Beverages> getBurgers(){
        return beveragesService.getAllBeverages();
    }

    @GetMapping("/categories")
    public List<String> getCategories(){
        return beveragesService.getAllCategories();
    }

    @GetMapping("/all/{categories}")
    public List<Beverages> categorizedItems(@PathVariable String categories){
        return beveragesService.getCategories(categories);
    }

    @PutMapping("/add")
    public void addBurger(@RequestBody BeveragesDTO beveragesDTO ){
        beveragesService.addBeverage(beveragesDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBurger(@RequestBody String code){
        beveragesService.deleteBeverage(code);
        return ResponseEntity.ok("Burger deleted successfully");

    }
}
