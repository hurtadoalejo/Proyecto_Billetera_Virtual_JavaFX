package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;

public class EstadisticasController {
    ModelFactory modelFactory;

    public EstadisticasController() {
        modelFactory = ModelFactory.getInstance();
    }

    public double obtenerSaldoPromedioUsuarios() {
        return modelFactory.obtenerSaldoPromedioUsuarios();
    }

    public UsuarioDto obtenerUsuarioMasTransacciones() {
        return modelFactory.obtenerUsuarioMasTransacciones();
    }
}