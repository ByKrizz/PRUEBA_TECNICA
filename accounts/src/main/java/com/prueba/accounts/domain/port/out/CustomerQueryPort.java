/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prueba.accounts.domain.port.out;

import com.prueba.accounts.domain.model.Customer;
import java.util.Optional;

/**
 *
 * @author chris
 */
public interface CustomerQueryPort {
    Optional<Customer> findById(Long id);
}
