/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.adapter.out.message;

import com.prueba.accounts.domain.model.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);

    Customer toDomain(CustomerDTO entity);
}
