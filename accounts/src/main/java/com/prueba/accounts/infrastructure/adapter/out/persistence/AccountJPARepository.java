/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.adapter.out.persistence;

import com.prueba.accounts.domain.model.Account;
import com.prueba.accounts.domain.port.out.AccountRepository;
import com.prueba.accounts.infrastructure.adapter.out.entity.AccountEntity;
import com.prueba.accounts.infrastructure.adapter.out.mapper.AccountMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 */
@Component
@RequiredArgsConstructor
public class AccountJPARepository  implements  AccountRepository{

     private final SpringDataAccountRepository jpaRepository;

   @Override
    public Account save(Account cuenta) {
        AccountEntity entity = AccountMapper.toEntity(cuenta); // mapear a entity
        AccountEntity saved = jpaRepository.save(entity);
        return AccountMapper.toDomain(saved); // mapear de vuelta a dominio
    }

    @Override
    public Optional<Account> findByNumberAccount(String numeroCuenta) {
        return jpaRepository.findByNumeroCuenta(numeroCuenta)
                .map(AccountMapper::toDomain);
    }

    @Override
    public List<Account> findByNumberCustomer(Long clienteId) {
        return jpaRepository.findByClienteId(clienteId)
                .stream()
                .map(AccountMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void updateBalance(String numeroCuenta, Double nuevoSaldo) {
        Optional<AccountEntity> optEntity = jpaRepository.findByNumeroCuenta(numeroCuenta);
        if (optEntity.isPresent()) {
            AccountEntity entity = optEntity.get();
            entity.setSaldoInicial(nuevoSaldo);
            jpaRepository.save(entity);
        } else {
            throw new RuntimeException("Cuenta no encontrada: " + numeroCuenta);
        }
    }


}