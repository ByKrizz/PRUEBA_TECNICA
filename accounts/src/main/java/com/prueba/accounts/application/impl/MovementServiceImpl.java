/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.application.impl;

import com.prueba.accounts.domain.model.Account;
import com.prueba.accounts.domain.model.Movements;
import com.prueba.accounts.domain.port.in.AccountService;
import com.prueba.accounts.domain.port.in.MovementService;
import com.prueba.accounts.domain.port.out.MovementRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountServiceImpl accountService;

    @Transactional
    @Override
    public Movements saveMovement(Movements movimiento) {
        // Validaciones básicas
        if (movimiento.getValor() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0");
        }
        if (!movimiento.getTipoMovimiento().equalsIgnoreCase("DEBITO")
                && !movimiento.getTipoMovimiento().equalsIgnoreCase("CREDITO")) {
            throw new IllegalArgumentException("Tipo de movimiento inválido");
        }
        if (movimiento.getFecha() == null) {
            movimiento.setFecha(LocalDate.now());
        }

        Account cuenta = accountService.getAccountNumber(movimiento.getCuenta().getNumero_cuenta())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        if (movimiento.getTipoMovimiento().equalsIgnoreCase("DEBITO")) {
            if (cuenta.getSaldo_disponible() < movimiento.getValor()) {
                throw new IllegalStateException("Saldo insuficiente");
            }
//            cuenta.setSaldo_inicial(cuenta.getSaldo_inicial() - movimiento.getSaldoDisponible());
            cuenta.setSaldo_disponible(cuenta.getSaldo_disponible() - movimiento.getValor());
            System.out.println("saldo: " + cuenta.getSaldo_disponible());
            movimiento.setSaldoDisponible(cuenta.getSaldo_disponible() - movimiento.getValor());
            movimiento.getCuenta().setSaldo_disponible(cuenta.getSaldo_disponible() - movimiento.getValor());
        } else {
//            cuenta.setSaldo_inicial(cuenta.getSaldo_inicial() + movimiento.getSaldoDisponible());
            cuenta.setSaldo_disponible(cuenta.getSaldo_disponible() + movimiento.getValor());
            movimiento.setSaldoDisponible(cuenta.getSaldo_disponible() - movimiento.getValor());

            System.out.println("saldo: " + cuenta.getSaldo_disponible());
            movimiento.getCuenta().setSaldo_disponible(cuenta.getSaldo_disponible() + movimiento.getValor());
        }

        accountService.updateBalance(cuenta.getNumero_cuenta(), cuenta.getSaldo_disponible());
        return movementRepository.save(movimiento);
    }

    @Override
    public List<Movements> getMovementAccount(String numeroCuenta) {
        return movementRepository.findByNumberAccount(numeroCuenta);
    }

    @Override
    public List<Movements> getMovementCustomer(Long clienteId, LocalDate desde, LocalDate hasta) {
        return movementRepository.findByCustomerDate(clienteId, desde, hasta);
    }

}
