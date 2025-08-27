/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.customers.application;

import com.prueba.customers.application.impl.CustomerServicesImpl;
import com.prueba.customers.domain.model.Customer;
import com.prueba.customers.domain.port.out.CustomerRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author chris
 */
public class CustomerServicesImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerPublisher customerEventPublisher;

    @InjectMocks
    private CustomerServicesImpl customerService;

     private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    private List<Customer> customers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer1 = new Customer();
        customer1.setId(1L);
        customer1.setNombre("Christopher");
        customer1.setTelefono("0912345678");
        customer1.setDireccion("AV A entre B y C");
        customer1.setPassword("123456789.");

        customer2 = new Customer();
        customer2.setId(2L);
        customer2.setNombre("Andrea");
        customer2.setTelefono("0998765432");
        customer2.setDireccion("Calle X entre Y y Z");
        customer2.setPassword("abcdef123.");

        customer3 = new Customer();
        customer3.setId(3L);
        customer3.setNombre("Carlos");
        customer3.setTelefono("0981122334");
        customer3.setDireccion("Av Principal y Calle Secundaria");
        customer3.setPassword("pass321#");

        customers = Arrays.asList(customer1, customer2, customer3);
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.save(customer1)).thenReturn(customer1);

        Customer saved = customerService.createCustomer(customer1);

        assertNotNull(saved);
        assertEquals("Christopher", saved.getNombre());
        verify(customerRepository, times(1)).save(customer1);
    }

    @Test
    void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));

        Optional<Customer> result = customerService.getCustomerById(1L);

        assertTrue(result.isPresent());
        assertEquals("Christopher", result.get().getNombre());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllCustomer() {
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomer();
        System.out.println("Index 3: "+result.get(2));
        System.out.println("Tamaño: "+result.size());
        assertEquals(3, result.size());
        assertEquals("Andrea", result.get(1).getNombre());
        verify(customerRepository, times(1)).findAll();
    }

    
    @Test
    void testProcessCustomerEvent() {
        when(customerRepository.save(customer1)).thenReturn(customer1);

        customerService.processCustomerEvent(customer1);

        verify(customerRepository, times(1)).save(customer1);
        verify(customerEventPublisher, times(1)).publishCustomerEvent(customer1);
    }
}
