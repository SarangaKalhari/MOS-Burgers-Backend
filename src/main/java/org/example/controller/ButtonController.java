package org.example.controller;

import org.example.model.dto.CategoryDTO;
import org.example.model.entity.Category;
import org.example.service.ButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/buttons")
@CrossOrigin("http://localhost:4200/")
public class ButtonController {

    @Autowired
    ButtonService buttonService;

    @GetMapping
    public List<CategoryDTO> getButtons(){
        return buttonService.getButtons();
    }
}
