package org.example.repository;

import org.example.model.dto.AppetizersDTO;
import org.example.model.entity.Appetizers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppetizersRepository extends JpaRepository<Appetizers, Long> {

    void deleteByCode();

    @Query("SELECT DISTINCT a.category FROM Appetizers a")
    List<String> getAllCategories();

    List<Appetizers> findByCategories(String categories);
}
