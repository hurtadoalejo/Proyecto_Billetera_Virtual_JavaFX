package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;

import java.time.LocalDate;
import java.util.LinkedList;

public class ReporteFinancieroController {
    ModelFactory modelFactory;

    public ReporteFinancieroController() {
        modelFactory = ModelFactory.getInstance();
    }

    public LinkedList<TransaccionDto> obtenerListaTransaccionesGastos(String idUsuario, LocalDate fechaInicio, LocalDate fechaFin) {
        return modelFactory.obtenerListaTransaccionesGastos(idUsuario, fechaInicio, fechaFin);
    }

    public LinkedList<TransaccionDto> obtenerListaTransaccionesIngresos(String idUsuario, LocalDate fechaInicio, LocalDate fechaFin) {
        return modelFactory.obtenerListaTransaccionesIngresos(idUsuario, fechaInicio, fechaFin);
    }

    public LinkedList<CuentaDto> obtenerListaCuentas(String idUsuario) {
        return modelFactory.obtenerCuentasUsuario(idUsuario);
    }
}