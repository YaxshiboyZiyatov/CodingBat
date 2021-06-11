package com.example.codingbat.repository;

import com.example.codingbat.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByNameAndDescription(String name, String description);
}
