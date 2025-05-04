package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;

import java.util.LinkedList;

public class GestionCuentasController {
    ModelFactory modelFactory;

    public GestionCuentasController() {
        modelFactory = ModelFactory.getInstance();
    }

    public LinkedList<CuentaDto> obtenerCuentas() {
        return modelFactory.obtenerCuentas();
    }

    public boolean agregarCuenta(CuentaDto cuentaDto) {
        return modelFactory.agregarCuenta(cuentaDto);
    }

    public boolean eliminarCuenta(int idCuenta, String numCuenta) {
        return modelFactory.eliminarCuenta(idCuenta, numCuenta);
    }

    public boolean actualizarCuenta(CuentaDto cuentaVieja, CuentaDto cuentaNueva) {
        return modelFactory.actualizarCuenta(cuentaVieja, cuentaNueva);
    }

    public LinkedList<String> obtenerUsuariosId() {
        return modelFactory.obtenerUsuariosId();
    }

    public boolean verificarCuentaNumCuenta(String numCuenta) {
        return modelFactory.verificarCuentaNumCuenta(numCuenta);
    }

    public boolean verificarCuentaId(int id) {
        return modelFactory.verificarCuentaId(id);
    }

    public LinkedList<String> obtenerPresupuestosDisponiblesUsuario(String usuario) {
        return modelFactory.obtenerPresupuestosDisponiblesUsuario(usuario);
    }
}