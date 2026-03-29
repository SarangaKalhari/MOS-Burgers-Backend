package org.example.repository;

import org.example.model.entity.Appetizers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppetizersRepository extends JpaRepository<Appetizers, Long> {

    void deleteByCode(String code);

    @Query("SELECT DISTINCT a.category FROM Appetizers a")
    List<String> getAllCategories();

    List<Appetizers> findByCategory(String category);
}
