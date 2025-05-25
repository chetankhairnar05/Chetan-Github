package com.natche.ToDoComplex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natche.ToDoComplex.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCategoryId(Long categoryId);
    Optional<Task>findByTitle(String title);
    // boolean existsByTitleAndCategoryId(String title, Long categoryId);
    Optional<Task> findByTitleAndCategoryId(String title,Long categoryId);
}
