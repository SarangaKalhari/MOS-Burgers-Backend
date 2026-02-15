package org.example.service;

import org.example.model.dto.BeveragesDTO;
import org.example.model.entity.Beverages;
import org.example.model.entity.Burger;
import org.example.repository.BeveragesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeveragesService {

    @Autowired
    BeveragesRepository beveragesRepository ;

    public List<Beverages> getAllBeverages() {
        return beveragesRepository.findAll();
    }

    public List<String> getAllCategories() {
        return beveragesRepository.getAllCategories();
    }

    public List<Beverages> getCategories(String categories) {
        return beveragesRepository.findByCategory(categories);
    }
}
