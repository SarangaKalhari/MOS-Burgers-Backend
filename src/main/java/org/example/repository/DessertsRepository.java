package org.example.repository;

import org.example.model.entity.Appetizers;
import org.example.model.entity.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DessertsRepository extends JpaRepository<Dessert, Long> {

    void deleteByCode(String code);

    List<Appetizers> findByCategory(String categories);

    @Query("SELECT DISTINCT d.category FROM desserts d")
    List<String> getCategories();
}
