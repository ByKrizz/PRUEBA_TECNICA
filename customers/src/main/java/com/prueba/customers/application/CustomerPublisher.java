/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.customers.application;

import com.prueba.customers.domain.model.Customer;

/**
 *
 * @author chris
 */
public interface CustomerPublisher {
    void publishCustomerEvent(Customer customer);
}