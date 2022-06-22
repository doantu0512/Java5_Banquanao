package com.example.Assignment_Java5.repository;

import com.example.Assignment_Java5.entitys.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
    Account findByEmail(String email);
}