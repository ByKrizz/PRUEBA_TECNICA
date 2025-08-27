/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.customers.infrastructure.adapter.out.kafka;

import com.prueba.customers.application.CustomerPublisher;
import com.prueba.customers.domain.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerProducer implements CustomerPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "customer-events";

    @Override
    public void publishCustomerEvent(Customer customer) {
        try {
            String message = objectMapper.writeValueAsString(customer);
            kafkaTemplate.send(TOPIC, message);
            log.info("Evento Kafka enviado: {}", customer);
        } catch (JsonProcessingException e) {
            log.error("Error convirtiendo Customer a JSON", e);
        }
    }
}
//    {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final ObjectMapper objectMapper;
//
//    private final String topic = "customer-events"; // o inyectar desde properties
//
//    public void sendCustomerEvent(Customer customer) {
//        try {
//            String message = objectMapper.writeValueAsString(customer);
//            kafkaTemplate.send(topic, message);
//            log.info("Mensaje enviado a Kafka: {}", message);
//        } catch (JsonProcessingException e) {
//            log.error("Error convirtiendo Customer a JSON", e);
//        }
//    }
//}
