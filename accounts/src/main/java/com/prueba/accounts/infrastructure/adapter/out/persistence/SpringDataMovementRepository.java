/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.adapter.out.persistence;

import com.prueba.accounts.infrastructure.adapter.out.entity.MovementEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chris
 */
@Repository
public interface SpringDataMovementRepository extends JpaRepository<MovementEntity, Long> {

    List<MovementEntity> findByAccount_NumeroCuenta(String numeroCuenta);

}
