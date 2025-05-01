package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;

import java.util.LinkedList;

public class GestionCuentasUsuarioController {
    ModelFactory modelFactory;

    public GestionCuentasUsuarioController() {
        modelFactory = ModelFactory.getInstance();
    }

    public LinkedList<CuentaDto> obtenerCuentas(String idUsuario) {
        return modelFactory.obtenerCuentasUsuario(idUsuario);
    }

    public boolean agregarCuentaUsuario(String idUsuario, CuentaDto cuentaDto) {
        return modelFactory.agregarCuentaUsuario(idUsuario, cuentaDto);
    }

    public boolean eliminarCuentaUsuario(String idUsuario, int idCuenta, String numCuenta) {
        return modelFactory.eliminarCuentaUsuario(idUsuario, idCuenta, numCuenta);
    }

    public boolean actualizarCuentaUsuario(String idUsuario, CuentaDto cuentaVieja, CuentaDto cuentaNueva) {
        return modelFactory.actualizarCuentaUsuario(idUsuario, cuentaVieja, cuentaNueva);
    }

    public boolean verificarCuentaNumCuenta(String numCuenta) {
        return modelFactory.verificarCuentaNumCuenta(numCuenta);
    }

    public boolean verificarCuentaId(int id) {
        return modelFactory.verificarCuentaId(id);
    }

    public LinkedList<String> obtenerPresupuestosDisponiblesNombres(String idUsuario) {
        return modelFactory.obtenerPresupuestoDisponiblesNombres(idUsuario);
    }
}