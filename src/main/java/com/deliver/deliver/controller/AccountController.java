package com.deliver.deliver.controller;

import com.deliver.deliver.entity.Account;
import com.deliver.deliver.entity.AccountWrapper;
import com.deliver.deliver.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/account")
public class AccountController {


    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path="/getAccounts", produces = "application/json")
    public List<AccountWrapper> getAccounts() {
        return  this.accountService.getAccounts();

    }

    @RequestMapping(method=RequestMethod.POST,  path = "/newAccount",  produces = "application/json",  consumes = "application/json")
    public  ResponseEntity<String>   newAccount(@RequestBody Account account) {

        try {
            this.accountService.validateData(account);
            this.accountService.save(account);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("success");

    }

}
