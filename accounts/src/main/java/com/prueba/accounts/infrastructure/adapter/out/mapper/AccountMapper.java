/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.adapter.out.mapper;

import com.prueba.accounts.domain.model.Account;
import com.prueba.accounts.infrastructure.adapter.out.entity.AccountEntity;

/**
 *
 * @author chris
 */
public class AccountMapper {

    public static AccountEntity toEntity(Account account) {
        if (account == null) {
            return null;
        }

        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setNumeroCuenta(account.getNumero_cuenta());
        entity.setTipoCuenta(account.getTipo_cuenta());
        entity.setSaldoInicial(account.getSaldo_inicial());
        entity.setSaldoDisponible(account.getSaldo_inicial()); // inicializamos disponible igual al saldo inicial
        entity.setEstado(Boolean.parseBoolean(account.getEstado())); // si tu Account tiene String
        entity.setClienteId(account.getClienteId());
        entity.setFechaCreacion(java.time.LocalDateTime.now());
        return entity;
    }

    public static Account toDomain(AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        Account account = new Account();
        account.setId(entity.getId());
        account.setNumero_cuenta(entity.getNumeroCuenta());
        account.setTipo_cuenta(entity.getTipoCuenta());
        account.setSaldo_inicial(entity.getSaldoInicial());
        account.setEstado(entity.getEstado().toString()); // si tu Account tiene String
        account.setClienteId(entity.getClienteId());
        return account;
    }
}
