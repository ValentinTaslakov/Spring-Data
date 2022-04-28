package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.models.User;
import com.example.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void withdrawMoney(BigDecimal amount, int id) {

        Account searched = accountRepository.findAccountById(id);

        if (searched != null ) {
            BigDecimal balance = searched.getBalance();
            if (balance.compareTo(amount) >= 0){
                searched.setBalance(balance.subtract(amount));
                accountRepository.save(searched);
            }
        }

    }

    @Override
    public void transferMoney(BigDecimal amount, int id) {

    }

    @Override
    public void registerAccount(Account account) {
        accountRepository.save(account);
    }
}
