package org.example.service;

import org.example.model.dto.BurgerDTO;
import org.example.model.entity.Burger;
import org.example.repository.BurgerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BurgerService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    BurgerRepository burgerRepository;

    public List<Burger> getAllBurgers() {
        return burgerRepository.findAll();
    }

    public List<Burger> getCategories(String categories) {
        return burgerRepository.findByCategory(categories);
    }

    public List<String> getAllCategories() {
        return burgerRepository.getAllCategories();
    }

    public void addBurger(BurgerDTO burgerDTO) {
        Burger burger = modelMapper.map(burgerDTO, Burger.class);
        burgerRepository.save(burger);
    }

    public void deleteBurger(String code) {
        burgerRepository.deleteByCode(code);
    }
}
