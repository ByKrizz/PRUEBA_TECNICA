/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.adapter.out.persistence;

import com.prueba.accounts.infrastructure.adapter.out.entity.AccountEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chris
 */
@Repository
public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByNumeroCuenta(String numeroCuenta);

    List<AccountEntity> findByClienteId(Long clienteId);
}