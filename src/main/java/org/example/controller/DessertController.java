package org.example.controller;

import org.example.model.dto.DessertDTO;
import org.example.service.DessertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dessert")
public class DessertController {

    @Autowired
    DessertsService dessertsService;

    @GetMapping
    public List<DessertDTO> getAllDesserts(){
        return dessertsService.getDesserts();
    }
}
