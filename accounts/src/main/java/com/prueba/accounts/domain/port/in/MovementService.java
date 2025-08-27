/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.domain.port.in;

import com.prueba.accounts.domain.model.Movements;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author chris
 */
public interface MovementService {
    
    Movements saveMovement(Movements movimiento);

    List<Movements> getMovementAccount(String numeroCuenta);

    List<Movements> getMovementCustomer(Long clienteId, LocalDate desde, LocalDate hasta);

}