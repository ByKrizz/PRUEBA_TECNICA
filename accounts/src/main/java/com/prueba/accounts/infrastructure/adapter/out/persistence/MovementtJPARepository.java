/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.adapter.out.persistence;

import com.prueba.accounts.domain.model.Movements;
import com.prueba.accounts.domain.port.out.MovementRepository;
import com.prueba.accounts.infrastructure.adapter.out.entity.MovementEntity;
import com.prueba.accounts.infrastructure.adapter.out.mapper.MovementMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 */
@Component
@RequiredArgsConstructor
public class MovementtJPARepository implements MovementRepository {

    private final EntityManager entityManager;
    private final SpringDataMovementRepository movementRepositoryJpa;
    private final MovementMapper movementMapper;

    @Override
    @Transactional
    public Movements save(Movements movement) {
        MovementEntity entity = movementMapper.toEntity(movement); // usar el bean
        MovementEntity saved = movementRepositoryJpa.save(entity);
        return movementMapper.toDomain(saved);
    }
//    public Movements save(Movements movimiento) {
//        entityManager.persist(movimiento); 
//        return movimiento;
//    }

    @Override
    public List<Movements> findByNumberAccount(String numeroCuenta) {
        String jpql = "SELECT m FROM tbl_movements m , tbl_account a WHERE a.numeroCuenta = :numeroCuenta "
                + "AND m.account_id = a.id";
        return entityManager.createQuery(jpql, Movements.class)
                .setParameter("numeroCuenta", numeroCuenta)
                .getResultList();
    }

    @Override
    public List<Movements> findByCustomerDate(Long clienteId, LocalDate desde, LocalDate hasta) {
        LocalDateTime desdeDateTime = desde.atStartOfDay();
        LocalDateTime hastaDateTime = hasta.atTime(23, 59, 59);

        String jpql = "SELECT m FROM MovementEntity m "
                + "JOIN m.account a "
                + "WHERE a.clienteId = :clienteId "
                + "AND m.fecha BETWEEN :desde AND :hasta "
                + "ORDER BY m.fecha ASC";

        List<MovementEntity> entities = entityManager.createQuery(jpql, MovementEntity.class)
                .setParameter("clienteId", clienteId)
                .setParameter("desde", desdeDateTime)
                .setParameter("hasta", hastaDateTime)
                .getResultList();

        // Mapear toda la lista a la lista de dominio
        return movementMapper.toDomainList(entities);
    }

}
