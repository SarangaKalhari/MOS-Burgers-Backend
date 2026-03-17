package org.example.controller;

import org.example.model.dto.DessertDTO;
import org.example.model.entity.Appetizers;
import org.example.model.entity.Desserts;
import org.example.service.DessertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dessert")
@CrossOrigin("http://localhost:4200/")
public class DessertController {

    @Autowired
    DessertsService dessertsService;

    @GetMapping
    public List<Desserts> getAllDesserts(){
        return dessertsService.getDesserts();
    }

    @GetMapping("/categories")
    public List<String> getCategories(){
        return dessertsService.getAllCategories();
    }

    @GetMapping("/{categories}")
    public List<Appetizers> categorizedItems(@PathVariable String categories){
        return dessertsService.getCategories(categories);
    }

    @PutMapping("/add")
    public void addDessert(@RequestBody DessertDTO dessertDTO){
        dessertsService.addDessert(dessertDTO);
    }

    @DeleteMapping("/delete")
    public void  deleteDessert(@RequestBody String code){
        dessertsService.deleteDessert(code);
    }

}
