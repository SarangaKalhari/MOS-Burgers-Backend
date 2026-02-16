package org.example.controller;

import org.example.model.dto.AppetizersDTO;
import org.example.model.entity.Appetizers;
import org.example.model.entity.Burger;
import org.example.service.AppetizersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appetizers")
public class AppetizersController {

    @Autowired
    AppetizersService appetizersService;

    @GetMapping
    public List<AppetizersDTO> getAppetizers(){
        return appetizersService.getAll();
    }

    @GetMapping("/categories")
    public List<String> getCategories(){
        return appetizersService.getAllCategories();
    }

    @GetMapping("/{categories}")
    public List<Appetizers> categorizedItems(@PathVariable String categories){
        return appetizersService.getCategories(categories);
    }

    @PutMapping("/add")
    public void addAppetizer(@RequestBody AppetizersDTO appetizersDTO){
        appetizersService.addAppetizer(appetizersDTO);
    }

    @DeleteMapping("/delete")
    public void deleteAppetizer(@RequestBody String code){
        appetizersService.deleteAppetizers(code);
    }

}
