package com.example.Assignment_Java5.repository;

import com.example.Assignment_Java5.entitys.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}