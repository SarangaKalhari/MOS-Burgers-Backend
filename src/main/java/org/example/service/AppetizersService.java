package org.example.service;

import org.example.model.dto.AppetizersDTO;
import org.example.model.entity.Appetizers;
import org.example.repository.AppetizersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppetizersService {
    
    ModelMapper modelMapper = new ModelMapper();
    
    @Autowired
    AppetizersRepository appetizersRepository;

//     get categories in table
    public List<String> getAllCategories() {
        return appetizersRepository.getAllCategories();
    }

//    get categorized data
    public List<Appetizers> getCategories(String category) {
        return appetizersRepository.findByCategory(category);
    }

//    get all appetizers
    public List<AppetizersDTO> getAll() {
        List<Appetizers> appetizersList = appetizersRepository.findAll();

        return appetizersList.stream()
                .map(category -> modelMapper.map(appetizersList, AppetizersDTO.class))
                .toList();
    }

//    add  item to db
    public void addAppetizer(AppetizersDTO appetizersDTO){
        Appetizers appetizers = modelMapper.map(appetizersDTO, Appetizers.class);
        appetizersRepository.save(appetizers);
    }

//    delete item from db
    public void deleteAppetizers(String code){
        appetizersRepository.deleteByCode(code);
    }

}
