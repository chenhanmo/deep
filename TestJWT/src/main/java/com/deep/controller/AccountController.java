package com.deep.controller;

import com.deep.model.Account;
import com.deep.service.AccountService;
import com.deep.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    private List getAllAccounts(){
        return accountService.getAllAccount();
    }

    @GetMapping("/accounts/login")
    private Map<String,Object> login(@RequestBody Account account){
        Map<String,Object> map = new HashMap<>();
        try{
            Account account_h2 = accountService.login(account);
            Map<String,String> load = new HashMap<>();
            load.put("id", account_h2.getId().toString());
            load.put("name", account_h2.getName());

            String token = JWTUtils.getToken(load);
            map.put("state",true);
            map.put("msg", "Successful verification!");
            map.put("token", token);
        } catch (Exception e){
            map.put("state",false);
            map.put("msg", e.getMessage());
        }

        return map;
    }

    @GetMapping("/accounts/test")
    private Map<String,Object> test(){
        Map<String,Object> map = new HashMap<>();
        Map<String,String> load = new HashMap<>();
        load.put("id", "1");
        load.put("name", "Ben");
        String token = JWTUtils.getToken(load);
        map.put("token", token);
        return map;
    }

    @PostMapping("/accounts/test1")
    private Map<String,Object> test1(){
        Map<String,Object> map = new HashMap<>();
        map.put("state",true);
        map.put("msg", "Successful verification!");
        return map;
    }

    @GetMapping("/accounts/{id}")
    private Account getAccountById(@PathVariable("id") int id){
        return accountService.getAccountById(id);
    }

    @PostMapping("/accounts")
    private ResponseEntity createAccount(@RequestBody Account account) {
        try{
            accountService.saveOrUpdate(account);
        } catch (Exception exception){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("New account created.", HttpStatus.CREATED);
    }

    @DeleteMapping("/accounts/{id}")
    private ResponseEntity deleteAccount(@PathVariable("id") int id) {
        try{
            accountService.delete(id);
        } catch (Exception exception){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("Account deleted.", HttpStatus.OK);
    }
}
