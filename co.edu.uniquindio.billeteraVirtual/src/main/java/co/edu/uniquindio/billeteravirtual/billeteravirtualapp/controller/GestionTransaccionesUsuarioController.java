package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;

import java.util.LinkedList;

public class GestionTransaccionesUsuarioController {
    ModelFactory modelFactory;

    public GestionTransaccionesUsuarioController() {
        modelFactory = ModelFactory.getInstance();
    }

    public LinkedList<TransaccionDto> obtenerTransacciones(String idUsuario) {
        return modelFactory.obtenerTransacciones(idUsuario);
    }

    public LinkedList<String> obtenerCategoriasNombresDeUsuario(String idUsuario) {
        return modelFactory.obtenerCategoriasNombresDeUsuario(idUsuario);
    }

    public LinkedList<String> obtenerNumCuentasUsuario(String idUsuario) {
        return modelFactory.obtenerNumCuentasUsuario(idUsuario);
    }

    public int obtenerNuevoIdTransaccion() {
        return modelFactory.obtenerNuevoIdTransaccion();
    }

    public boolean agregarTransaccion(TransaccionDto transaccionDto, String idUsuario) {
        return modelFactory.agregarTransaccion(transaccionDto, idUsuario);
    }

    public boolean saldoCuentaEsSuficiente(TransaccionDto transaccion) {
        return modelFactory.saldoCuentaEsSuficiente(transaccion);
    }

    public boolean validarPresupuesto(TransaccionDto transaccion) {
        return modelFactory.validarPresupuesto(transaccion);
    }
}