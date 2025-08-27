/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.domain.port.out;

import com.prueba.accounts.domain.model.Movements;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author chris
 */
public interface MovementRepository {

    Movements save(Movements movimiento);
    List<Movements> findByNumberAccount(String numeroCuenta);
    List<Movements> findByCustomerDate(Long clienteId, LocalDate desde, LocalDate hasta);

}
