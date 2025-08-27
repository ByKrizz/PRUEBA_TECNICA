/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.gateway;

import com.prueba.accounts.domain.model.Customer;
import com.prueba.accounts.domain.port.out.CustomerGateway;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author chris
 */
@Component
public class CustomerGatewayRest implements CustomerGateway {

    private final RestTemplate restTemplate;
    private final String customerServiceUrl;

    public CustomerGatewayRest(RestTemplate restTemplate,
                               @Value("${customer.service.url}") String customerServiceUrl) {
        this.restTemplate = restTemplate;
        this.customerServiceUrl = customerServiceUrl;
    }


    @Override
    public Optional<Customer> findById(Long id) {
        String url = customerServiceUrl + "/customers/" + id;
        Customer customer = restTemplate.getForObject(url, Customer.class);
        return Optional.ofNullable(customer);
    }
}