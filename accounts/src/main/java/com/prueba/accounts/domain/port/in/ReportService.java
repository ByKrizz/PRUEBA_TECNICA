/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.domain.port.in;

import com.prueba.accounts.domain.model.Report;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author chris
 */
public interface ReportService {
    List<Report> generarReporte(Long clienteId, LocalDate desde, LocalDate hasta);

}
