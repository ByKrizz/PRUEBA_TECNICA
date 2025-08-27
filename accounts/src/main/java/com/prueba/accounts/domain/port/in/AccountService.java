/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.domain.port.in;

import com.prueba.accounts.domain.model.Account;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author chris
 */
public interface AccountService {

    Account createAccount(Account cuenta);
    Optional<Account> getAccountNumber(String numeroCuenta);
    List<Account> getAccountCustomer(Long clienteId);
    void updateBalance(String numeroCuenta, Double monto);
}
