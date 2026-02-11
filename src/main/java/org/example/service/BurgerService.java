package org.example.service;

import org.example.model.entity.Burger;
import org.example.repository.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BurgerService {

    @Autowired
    BurgerRepository burgerRepository;

    public List<Burger> getAllBurgers() {
        return burgerRepository.findAll();
    }
}
