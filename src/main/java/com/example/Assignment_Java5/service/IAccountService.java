package com.example.Assignment_Java5.service;

import com.example.Assignment_Java5.entitys.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {
    public List<Account> getAll();
    public Page<Account> findPageAll(Pageable pageable);
    public Account insert(Account account);
    public Account update(Account account);
    public Account delete(Integer id);
    public Account findById(Integer id);
    public Account findByEmail(String email);
}
