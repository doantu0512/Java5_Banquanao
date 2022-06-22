package com.example.Assignment_Java5.service;

import com.example.Assignment_Java5.entitys.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    public List<Category> getAll();
    public Page<Category> findPageAll(Pageable pageable);
    public Category insert(Category category);
    public Category update(Category category);
    public Category delete(Integer id);
    public Category findById(Integer id);
}
