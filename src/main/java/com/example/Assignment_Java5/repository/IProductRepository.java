package com.example.Assignment_Java5.repository;

import com.example.Assignment_Java5.entitys.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByName(String name);
    @Query("Select obj from Product obj")
    Page<Product> findAllProduct (Pageable pageable);
}
