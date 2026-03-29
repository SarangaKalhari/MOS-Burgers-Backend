package org.example.repository;

import org.example.model.entity.Beverages;
import org.example.model.entity.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeveragesRepository extends JpaRepository<Beverages, Long> {

    @Query("SELECT DISTINCT b.category FROM Beverages b")
    List<String> getAllCategories();

    List<Beverages> findByCategory(String categories);

    void deleteByCode(String code);

    Optional<Beverages> findByCode(String code);
}
