package org.example.repository;

import org.example.model.entity.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DessertsRepository extends JpaRepository<Dessert, Long> {
}
