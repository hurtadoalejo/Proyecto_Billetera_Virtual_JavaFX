package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Transaccion;

import java.util.LinkedList;

public class GestionDineroController {
    ModelFactory modelFactory;

    public GestionDineroController() {
        modelFactory = ModelFactory.getInstance();
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

    public boolean saldoCuentaEsSuficiente(TransaccionDto transaccion, String idUsuario) {
        return modelFactory.saldoCuentaEsSuficiente(transaccion, idUsuario);
    }

    public boolean cuentasExisten(String numCuentaOrigen, String numCuentaDestino) {
        return modelFactory.cuentasExisten(numCuentaOrigen, numCuentaDestino);
    }
}