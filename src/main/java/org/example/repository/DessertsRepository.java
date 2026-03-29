package org.example.repository;

import org.example.model.entity.Desserts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DessertsRepository extends JpaRepository<Desserts, Long> {

    void deleteByCode(String code);

    List<Desserts> findByCategory(String categories);

    @Query("SELECT DISTINCT d.category FROM Desserts d")
    List<String> getCategories();

    Desserts findByCode(String code);
}
