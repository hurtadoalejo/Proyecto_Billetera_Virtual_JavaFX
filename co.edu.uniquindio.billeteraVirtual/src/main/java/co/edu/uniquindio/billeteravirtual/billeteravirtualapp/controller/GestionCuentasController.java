package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;

import java.util.LinkedList;

public class GestionCuentasController {
    ModelFactory modelFactory;

    /**
     * Metodo constructor de la clase GestionCuentasController
     */
    public GestionCuentasController() {
        modelFactory = ModelFactory.getInstance();
    }

    /**
     * Metodo para obtener la lista de cuentas de la billetera virtual
     * @return Lista de cuentas
     */
    public LinkedList<CuentaDto> obtenerCuentas() {
        return modelFactory.obtenerCuentas();
    }

    /**
     * Metodo para agregar una cuenta a la lista de cuentas de billetera virtual
     * @param cuentaDto Cuenta a agregar
     * @return Booleano sobre si la accion fue completada exitosamente o no
     */
    public boolean agregarCuenta(CuentaDto cuentaDto) {
        return modelFactory.agregarCuenta(cuentaDto);
    }

    /**
     * Metodo para eliminar una cuenta de la lista de cuentas de billetera virtual
     * @param cuentaDto Cuenta a eliminar
     * @return Booleano sobre si la accion fue completada exitosamente o no
     */
    public boolean eliminarCuenta(CuentaDto cuentaDto) {
        return modelFactory.eliminarCuenta(cuentaDto);
    }

    /**
     * Metodo para actualizar una cuenta de la lista de cuentas billetera virtual
     * @param cuenta Cuenta antigua
     * @param cuentaNueva Cuenta con los datos nuevos
     * @return Booleano sobre si la accion fue completada exitosamente o no
     */
    public boolean actualizarCuenta(CuentaDto cuenta, CuentaDto cuentaNueva) {
        return modelFactory.actualizarCuenta(cuenta.idCuenta(), cuenta.numCuenta(), cuentaNueva);
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
}