package com.example.demo.services;

import com.example.demo.models.Account;

import java.math.BigDecimal;

public interface AccountService {

    void withdrawMoney(BigDecimal amount, int id);
    void transferMoney(BigDecimal amount, int id);

    void registerAccount(Account account);
}
