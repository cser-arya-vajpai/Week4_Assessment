package com.capgemini.Controller;

import com.capgemini.model.Account;
import com.capgemini.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    public Object createAccount(@RequestBody Account account) {
        return service.createAccount(account);
    }

    @GetMapping("/{id}")
    public Object getAccount(@PathVariable int id) {
        return service.getAccount(id);
    }

    @GetMapping
    public Object getAllAccounts() {
        return service.getAllAccounts();
    }

    @PutMapping("/{id}")
    public Object updateAccount(@PathVariable int id, @RequestBody Account account) {
        return service.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public Object deleteAccount(@PathVariable int id) {
        return service.deleteAccount(id);
    }

    @PostMapping("/deposit")
    public Object deposit(@RequestBody Map<String, Object> body) {
        int id = (int) body.get("accountId");
        Double amount = Double.valueOf(body.get("amount").toString());

        return service.deposit(id, amount);
    }

    @PostMapping("/withdraw")
    public Object withdraw(@RequestBody Map<String, Object> body) {
        int id = (int) body.get("accountId");
        Double amount = Double.valueOf(body.get("amount").toString());

        return service.withdraw(id, amount);
    }
}