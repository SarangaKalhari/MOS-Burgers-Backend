package org.example.service;

import org.example.model.dto.BeveragesDTO;
import org.example.model.dto.BurgerDTO;
import org.example.model.entity.Beverages;
import org.example.model.entity.Burger;
import org.example.repository.BeveragesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeveragesService {

    ModelMapper modelMapper = new ModelMapper();

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

    public void addBeverage(BeveragesDTO beveragesDTO) {
        Beverages beverages = modelMapper.map(beveragesDTO, Beverages.class);
        beveragesRepository.save(beverages);
    }

    public void deleteBeverage(String code) {
        beveragesRepository.deleteByCode(code);
    }
}
