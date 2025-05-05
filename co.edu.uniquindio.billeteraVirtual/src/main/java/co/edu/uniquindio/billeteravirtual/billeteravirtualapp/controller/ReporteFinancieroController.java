package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;

import java.util.LinkedList;

public class ReporteFinancieroController {
    ModelFactory modelFactory;

    public ReporteFinancieroController() {
        modelFactory = ModelFactory.getInstance();
    }

    public LinkedList<TransaccionDto> obtenerListaTransaccionesGastos(String idUsuario) {
        return modelFactory.obtenerListaTransaccionesGastos(idUsuario);
    }

    public LinkedList<TransaccionDto> obtenerListaTransaccionesIngresos(String idUsuario) {
        return modelFactory.obtenerListaTransaccionesIngresos(idUsuario);
    }

    public LinkedList<CuentaDto> obtenerListaCuentas(String idUsuario) {
        return modelFactory.obtenerCuentasUsuario(idUsuario);
    }
}