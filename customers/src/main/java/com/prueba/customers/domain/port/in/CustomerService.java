/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.customers.domain.port.in;

import com.prueba.customers.domain.model.Customer;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author chris
 */
public interface CustomerService {
    Customer createCustomer (Customer customer);
    Optional<Customer> getCustomerById (Long id);
    List<Customer> getAllCustomer();
    Customer updateCustomer (Long id,Customer customer);
    boolean deleteCustomer (Long id);
}