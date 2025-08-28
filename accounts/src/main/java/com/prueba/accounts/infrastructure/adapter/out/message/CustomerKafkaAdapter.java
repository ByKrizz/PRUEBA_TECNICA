/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.adapter.out.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.accounts.domain.model.Customer;
import com.prueba.accounts.domain.port.out.CustomerQueryPort;
import java.util.Optional;

import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 */
@Component
public class CustomerKafkaAdapter implements CustomerQueryPort {

    private CustomerKafkaConection customerKafkaConection;
    private CustomerMapper customerMapper;

    public CustomerKafkaAdapter(CustomerKafkaConection customerKafkaConection, CustomerMapper customerMapper) {
        this.customerKafkaConection = customerKafkaConection;
        this.customerMapper = customerMapper;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try {
            String jsonResponse = customerKafkaConection.requestMessageResponseMessage(String.valueOf(id));
            if (jsonResponse == null || jsonResponse.isBlank() || jsonResponse.equals("{}")) {
                return Optional.empty();
            }

            ObjectMapper mapper = new ObjectMapper();
            CustomerDTO customerDto = mapper.readValue(jsonResponse, CustomerDTO.class);

            return Optional.of(customerMapper.toDomain(customerDto));

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
