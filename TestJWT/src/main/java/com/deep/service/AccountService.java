package com.deep.service;

import com.deep.model.Account;
import com.deep.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AccountService {
    Account login(Account account);

    Map<String,Object> test();

    List getAllAccount();

    Account getAccountById(int id);

    void saveOrUpdate(Account account);

    void delete(int id);
}
