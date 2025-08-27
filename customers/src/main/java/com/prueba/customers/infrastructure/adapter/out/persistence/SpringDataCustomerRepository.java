/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.customers.infrastructure.adapter.out.persistence;

import com.prueba.customers.infrastructure.adapter.out.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chris
 */
@Repository
public interface SpringDataCustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
