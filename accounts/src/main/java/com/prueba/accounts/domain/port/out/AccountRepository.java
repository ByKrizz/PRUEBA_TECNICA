/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.domain.port.out;

import com.prueba.accounts.domain.model.Account;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author chris
 */
public interface AccountRepository {

    Account save(Account cuenta);
    Optional<Account> findByNumberAccount(String numeroCuenta);
    List<Account> findByNumberCustomer(Long clienteId);
    void updateBalance(String numeroCuenta, Double nuevoSaldo);
}
