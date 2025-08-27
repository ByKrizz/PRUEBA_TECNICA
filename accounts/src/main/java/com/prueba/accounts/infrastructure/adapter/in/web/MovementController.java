/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.adapter.in.web;

import com.prueba.accounts.application.impl.MovementServiceImpl;
import com.prueba.accounts.domain.model.Movements;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chris
 */
@RestController
@RequestMapping("/movements")
public class MovementController {
     private final MovementServiceImpl movementService;

    public MovementController(MovementServiceImpl movementService) {
        this.movementService = movementService;
    }

    @PostMapping
    public ResponseEntity<Movements> createMovement(@RequestBody Movements movement) {
        return ResponseEntity.ok(movementService.saveMovement(movement));
    }

    @GetMapping("/cuenta/{numeroCuenta}")
    public ResponseEntity<List<Movements>> getMovementsByAccount(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(movementService.getMovementAccount(numeroCuenta));
    }
}