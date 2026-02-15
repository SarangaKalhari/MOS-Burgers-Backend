package org.example.repository;

import org.example.model.entity.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BurgerRepository extends JpaRepository<Burger, Long> {
    List<Burger> findByCategory(String categories);

    @Query("SELECT DISTINCT b.category FROM Burger b")
    List<String> getAllCategories();
}
