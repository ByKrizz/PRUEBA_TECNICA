/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.adapter.in.web;

import com.prueba.accounts.application.impl.AccountServiceImpl;
import com.prueba.accounts.domain.model.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chris
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    //Test
    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Optional<Account>> getAccountByNumber(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(accountService.getAccountNumber(numeroCuenta));
    }

    @GetMapping("/customer/{clienteId}")
    public ResponseEntity<List<Account>> getAccountsByCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(accountService.getAccountCustomer(clienteId));
    }

    @PutMapping("/{numeroCuenta}/saldo")
    public ResponseEntity<Void> updateBalance(@PathVariable String numeroCuenta, @RequestParam Double monto) {
        accountService.updateBalance(numeroCuenta, monto);
        return ResponseEntity.ok().build();
    }
}
