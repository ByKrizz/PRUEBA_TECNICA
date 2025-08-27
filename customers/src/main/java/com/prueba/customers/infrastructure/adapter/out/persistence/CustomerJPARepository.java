/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.customers.infrastructure.adapter.out.persistence;

import com.prueba.customers.domain.model.Customer;
import com.prueba.customers.domain.port.out.CustomerRepository;
import com.prueba.customers.infrastructure.adapter.out.entity.CustomerEntity;
import com.prueba.customers.infrastructure.adapter.out.mapper.CustomerMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 */
@Component
@RequiredArgsConstructor
public class CustomerJPARepository implements CustomerRepository {

    private final SpringDataCustomerRepository repository;
    private final CustomerMapper mapper; 

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        CustomerEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Customer update(Customer customer) {
        return save(customer);
    }

    @Override
    public boolean deleteById(Long id) {
        repository.deleteById(id);
        return true;
    }
}
