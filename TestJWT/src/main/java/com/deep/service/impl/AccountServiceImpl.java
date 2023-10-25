package com.deep.service.impl;

import com.deep.model.Account;
import com.deep.repository.AccountRepository;
import com.deep.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account login(Account account) {
        Account account_h2 = accountRepository.findById(account.getId()).get();
        if(account_h2 != null){
            return account_h2;
        }
        throw new RuntimeException("Login failed!");
    }

    @Override
    public Map<String, Object> test() {
        Map<String,Object> map = new HashMap<>();
        map.put("state",true);
        map.put("msg","Success!");
        return map;
    }

    public List getAllAccount(){
        List accounts = new ArrayList();
        accountRepository.findAll().forEach(account -> accounts.add(account));
        return accounts;
    }

    public Account getAccountById(int id){
        return accountRepository.findById(id).get();
    }

    public void saveOrUpdate(Account account){
        accountRepository.save(account);
    }

    public void delete(int id){
        accountRepository.deleteById(id);
    }
}
