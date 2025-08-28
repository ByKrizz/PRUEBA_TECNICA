/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.application.impl;

import com.prueba.accounts.domain.model.Account;
import com.prueba.accounts.domain.port.in.AccountService;
import com.prueba.accounts.domain.port.out.AccountRepository;
import com.prueba.accounts.domain.port.out.CustomerGateway;
import com.prueba.accounts.domain.port.out.CustomerQueryPort;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerGateway customerGateway;
    private final CustomerQueryPort customerQueryPort;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerGateway customerGateway, CustomerQueryPort customerQueryPort) {
        this.accountRepository = accountRepository;
        this.customerGateway = customerGateway;
        this.customerQueryPort = customerQueryPort;
    }

    @Override
    public Account createAccount(Account cuenta) {

        //var cliente = customerGateway.findById(cuenta.getClienteId()); //anterior
        var cliente = customerQueryPort.findById(cuenta.getClienteId()); //nuevo
        if (cliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado con id: " + cuenta.getClienteId());
        }
        String numeroCuenta = "ACC-" + System.currentTimeMillis();
        cuenta.setNumero_cuenta(numeroCuenta);
        cuenta.setSaldo_disponible(cuenta.getSaldo_inicial());

        return accountRepository.save(cuenta);
    }

    @Override
    public Optional<Account> getAccountNumber(String numeroCuenta) {
        return accountRepository.findByNumberAccount(numeroCuenta);
    }

    @Override
    public List<Account> getAccountCustomer(Long clienteId) {
        return accountRepository.findByNumberCustomer(clienteId);
    }

    @Override
    public void updateBalance(String numeroCuenta, Double monto) {
        Optional<Account> cuentaOpt = accountRepository.findByNumberAccount(numeroCuenta);
        if (cuentaOpt.isEmpty()) {
            throw new IllegalArgumentException("Cuenta no encontrada con número: " + numeroCuenta);
        }

        Account cuenta = cuentaOpt.get();
        double nuevoSaldo = monto;

        cuenta.setSaldo_disponible(nuevoSaldo);
        accountRepository.updateBalance(numeroCuenta, nuevoSaldo);
    }

}
