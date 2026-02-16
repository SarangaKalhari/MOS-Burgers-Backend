package org.example.service;

import org.example.model.dto.DessertDTO;
import org.example.model.entity.Appetizers;
import org.example.model.entity.Desserts;
import org.example.repository.DessertsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DessertsService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    DessertsRepository dessertsRepository;

    public List<DessertDTO> getDesserts(){
        List<Desserts> desserts = dessertsRepository.findAll();

        return desserts.stream()
                .map(category -> modelMapper.map(desserts, DessertDTO.class))
                .toList();
    }

    public void addDessert(DessertDTO dessertDTO) {
        Desserts dessert = modelMapper.map(dessertDTO, Desserts.class);
        dessertsRepository.save(dessert);
    }

    public void deleteDessert(String code) {
        dessertsRepository.deleteByCode(code);
    }

    public List<String> getAllCategories() {
        return dessertsRepository.getCategories();
    }

    public List<Appetizers> getCategories(String categories) {
        return dessertsRepository.findByCategory(categories);
    }
}
