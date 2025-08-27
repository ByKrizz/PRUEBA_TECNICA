/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.customers.application.impl;

import com.prueba.customers.domain.model.Customer;
import com.prueba.customers.domain.port.in.CustomerService;
import com.prueba.customers.domain.port.out.CustomerRepository;
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
public class CustomerServicesImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        customer.setId(id);
        return customerRepository.update(customer);
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return customerRepository.deleteById(id);
    }
}
