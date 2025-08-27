/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.customers.infrastructure.adapter.out.mapper;

import com.prueba.customers.domain.model.Customer;
import com.prueba.customers.infrastructure.adapter.out.entity.CustomerEntity;
import org.mapstruct.Mapper;

/**
 *
 * @author chris
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toEntity(Customer customer);

    Customer toDomain(CustomerEntity entity);

}
