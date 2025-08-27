/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.prueba.accounts.domain.model;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author chris
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movements {

    private Long id;
    private LocalDate fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldoDisponible;
    private Account cuenta;    
    
}
