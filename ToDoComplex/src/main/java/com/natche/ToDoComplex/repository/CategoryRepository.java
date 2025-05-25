package com.natche.ToDoComplex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natche.ToDoComplex.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // List<Category> findByUserId(Long userId);
    // boolean existsByName(String newName);
    Optional<Category> findByName(String name);
    // boolean existsByUserIdAndName(Long userId, String name);


}
