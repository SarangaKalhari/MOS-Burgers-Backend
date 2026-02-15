package org.example.repository;

import org.example.model.entity.Beverages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeveragesRepository extends JpaRepository<Beverages, Long> {

    @Query("SELECT DISTINCT b.category FROM Beverages b")
    List<String> getAllCategories();
}
