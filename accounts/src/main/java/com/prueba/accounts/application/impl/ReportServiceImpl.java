/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.application.impl;

import com.prueba.accounts.domain.model.Account;
import com.prueba.accounts.domain.model.Customer;
import com.prueba.accounts.domain.model.Report;
import com.prueba.accounts.domain.port.in.ReportService;
import com.prueba.accounts.domain.port.out.AccountRepository;
import com.prueba.accounts.domain.port.out.CustomerGateway;
import com.prueba.accounts.domain.port.out.MovementRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final CustomerGateway customerGateway;

    @Override
    public List<Report> generarReporte(Long clienteId, LocalDate desde, LocalDate hasta) {
        Optional<Customer> cliente = customerGateway.findById(clienteId);

        var movimientos = movementRepository.findByCustomerDate(clienteId, desde, hasta);

        return movimientos.stream().map(mov -> {
            Account cuenta = mov.getCuenta();
            return Report.builder()
                    .fecha(mov.getFecha())
                    .cliente(cliente.get().getNombre())
                    .numeroCuenta(cuenta.getNumero_cuenta())
                    .tipo(cuenta.getTipo_cuenta())
                    .saldoInicial(cuenta.getSaldo_inicial())
                    .estado(true)
                    .movimiento(mov.getValor())
                    .saldoDisponible(mov.getSaldoDisponible())
                    .build();
        }).toList();
    }
}
