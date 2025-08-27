/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.application.impl;

import com.prueba.accounts.domain.model.Account;
import com.prueba.accounts.domain.port.in.AccountService;
import com.prueba.accounts.domain.port.out.AccountRepository;
import com.prueba.accounts.domain.port.out.CustomerGateway;
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

    public AccountServiceImpl(AccountRepository accountRepository, CustomerGateway customerGateway) {
        this.accountRepository = accountRepository;
        this.customerGateway = customerGateway;
    }

    @Override
    public Account createAccount(Account cuenta) {
        // Verificar si el cliente existe en el servicio externo
        var cliente = customerGateway.findById(cuenta.getClienteId());
        if (cliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado con id: " + cuenta.getClienteId());
        }
        String numeroCuenta = "ACC-" + System.currentTimeMillis();
        cuenta.setNumero_cuenta(numeroCuenta);

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
        double nuevoSaldo = cuenta.getSaldo_inicial() + monto;

        if (nuevoSaldo < 0) {
            throw new IllegalStateException("Saldo no disponible");
        }

        cuenta.setSaldo_inicial(nuevoSaldo);
        accountRepository.save(cuenta);
    }

}
