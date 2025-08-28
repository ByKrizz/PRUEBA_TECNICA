/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.adapter.out.mapper;

import com.prueba.accounts.domain.model.Movements;
import com.prueba.accounts.infrastructure.adapter.out.entity.AccountEntity;
import com.prueba.accounts.infrastructure.adapter.out.entity.MovementEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 */
@Component
@RequiredArgsConstructor
public class MovementMapper {

    private final AccountMapper accountMapper;

    public Movements toDomain(MovementEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Movements(
                entity.getId(),
                entity.getFecha().toLocalDate(),
                entity.getTipoMovimiento(),
                entity.getMonto(),
                entity.getSaldoDisponible(),
                accountMapper.toDomain(entity.getAccount())
        );
    }

    public MovementEntity toEntity(Movements domain) {
        if (domain == null) {
            return null;
        }
        return MovementEntity.builder()
                .id(domain.getId())
                .fecha(domain.getFecha().atStartOfDay())
                .tipoMovimiento(domain.getTipoMovimiento())
                .monto(domain.getValor())
                .saldoDisponible(domain.getSaldoDisponible())
                .estado(true) // por defecto
                .account(accountMapper.toEntity(domain.getCuenta()))
                .build();
    }

    public List<Movements> toDomainList(List<MovementEntity> entities) {
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}

//public class MovementMapper {
//
//    public static MovementEntity toEntity(Movements movimiento, AccountEntity accountEntity) {
//        if (movimiento == null) {
//            return null;
//        }
//
//        MovementEntity entity = new MovementEntity();
//        entity.setId(movimiento.getId());
//        entity.setFecha(movimiento.getFecha() != null
//                ? movimiento.getFecha().atStartOfDay()
//                : java.time.LocalDateTime.now());
//        entity.setTipoMovimiento(movimiento.getTipoMovimiento());
//        entity.setMonto(movimiento.getValor());
//        entity.setSaldoDisponible(movimiento.getSaldoDisponible());
//        entity.setEstado(true); // inicializamos como activo
//        entity.setAccount(accountEntity);
//        return entity;
//    }
//
//    public static Movements toDomain(MovementEntity entity) {
//        if (entity == null) {
//            return null;
//        }
//
//        Movements movimiento = new Movements();
//        movimiento.setId(entity.getId());
//        movimiento.setFecha(entity.getFecha().toLocalDate());
//        movimiento.setTipoMovimiento(entity.getTipoMovimiento());
//        movimiento.setValor(entity.getMonto());
//        movimiento.setSaldoDisponible(entity.getSaldoDisponible());
//        movimiento.setCuenta(AccountMapper.toDomain(entity.getAccount()));
//        return movimiento;
//    }
//}
