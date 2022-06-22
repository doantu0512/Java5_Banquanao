package com.example.Assignment_Java5.repository;

import com.example.Assignment_Java5.entitys.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
}
