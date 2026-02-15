package org.example.controller;

import org.example.model.entity.Beverages;
import org.example.service.BeveragesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("beverages")
@CrossOrigin("http://localhost:4200/")
public class BeveragesController {

    @Autowired
    BeveragesService beveragesService;

    @GetMapping("/all")
    public List<Beverages> getBurgers(){
        return beveragesService.getAllBeverages();
    }

    @GetMapping("/categories")
    public List<String> getCategories(){
        return beveragesService.getAllCategories();
    }
}
