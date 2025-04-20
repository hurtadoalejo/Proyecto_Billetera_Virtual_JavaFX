package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;

import java.util.LinkedList;

public class GestionTransaccionesUsuarioController {
    ModelFactory modelFactory;

    public GestionTransaccionesUsuarioController() {
        modelFactory = ModelFactory.getInstance();
    }

    public boolean actualizarTransaccion(int idTransaccion, TransaccionDto transaccionDto) {
        return modelFactory.actualizarTransaccion(idTransaccion, transaccionDto);
    }

    public LinkedList<String> obtenerCategoriasPorNombreUsuario(String idUsuario) {
        return modelFactory.obtenerCategoriasPorNombreUsuario(idUsuario);
    }

    public LinkedList<TransaccionDto> obtenerTransacciones(String idUsuario) {
        return modelFactory.obtenerTransacciones(idUsuario);
    }

    public boolean transaccionPasaPresupuesto(String idUsuario, TransaccionDto transaccion) {
        return modelFactory.transaccionPasaPresupuesto(idUsuario, transaccion);
    }
}