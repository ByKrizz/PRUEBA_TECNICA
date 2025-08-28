/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.customers.infrastructure.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.customers.domain.model.Customer;
import com.prueba.customers.domain.port.in.CustomerEventsListenerPort;
import com.prueba.customers.domain.port.in.CustomerService;
import java.util.Optional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 */
@Component
public class CustomerConsumerAdapter implements CustomerEventsListenerPort {

    private final CustomerService customerService;

    public CustomerConsumerAdapter(CustomerService customerService) {
        this.customerService = customerService;
    }

    @KafkaListener(topics = "${kafka.request.topic}")
    @SendTo
    @Override
    public String consumeCustomerEvent(String customerId) {

        Optional<Customer> customer = customerService.getCustomerById(Long.valueOf(customerId));

        if (customer.isPresent()) {
            try {
                Customer sendCustomer = customer.get();
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(sendCustomer);
            } catch (JsonProcessingException e) {
                return "{}";
            }
        }

        return "{}";
    }

}
