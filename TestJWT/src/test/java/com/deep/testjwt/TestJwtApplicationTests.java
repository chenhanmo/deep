package com.deep.testjwt;

import com.deep.model.Account;
import com.deep.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestJwtApplicationTests {
    @Autowired
    AccountService accountService;

    @Test
    void contextLoads() {
        Account account = accountService.getAccountById(2);
        System.out.println(account.getName());
    }

}
