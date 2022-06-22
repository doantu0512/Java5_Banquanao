package com.example.Assignment_Java5.repository;

import com.example.Assignment_Java5.entitys.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}