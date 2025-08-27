/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.prueba.accounts.domain.model;

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
public class Account {

    private Long id;
    private String numero_cuenta;
    private String tipo_cuenta;
    private Double saldo_inicial;
    private String estado;
    private Long clienteId;

}
