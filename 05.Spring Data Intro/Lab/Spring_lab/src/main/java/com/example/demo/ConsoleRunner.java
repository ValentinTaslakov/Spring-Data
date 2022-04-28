package com.example.demo;

import com.example.demo.models.Account;
import com.example.demo.models.User;
import com.example.demo.services.AccountService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        User first = new User("Tino", 22);



        User second = new User("Dino", 22);
        userService.registerUser(second);

        Account account = new Account(new BigDecimal("25000"),first);

        first.setAccounts(new HashSet<>(){{
            add(account);
        }});
        userService.registerUser(first);
        /*accountService.registerAccount(account);*/
        accountService.withdrawMoney(new BigDecimal(20000), account.getId());
    }
}
