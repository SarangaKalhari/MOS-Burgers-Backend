package org.example.service;

import org.example.model.dto.CategoryDTO;
import org.example.model.entity.Category;
import org.example.repository.ButtonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ButtonService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ButtonRepository buttonRepository;

    public List<CategoryDTO> getButtons() {
        List<Category> categories = buttonRepository.findAll();

        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
    }

}
