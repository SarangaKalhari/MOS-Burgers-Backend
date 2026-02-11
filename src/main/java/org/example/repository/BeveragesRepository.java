package org.example.repository;

import org.example.model.entity.Beverages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeveragesRepository extends JpaRepository<Beverages, Long> {
}
