/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.customers.domain.port.out;

import com.prueba.customers.domain.model.Customer;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author chris
 */
public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    Customer update(Customer customer);
    boolean deleteById(Long id);
}