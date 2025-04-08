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
     * @param idCuenta Id de la cuenta a eliminar
     * @return Booleano sobre si la accion fue completada exitosamente o no
     */
    public boolean eliminarCuenta(int idCuenta, String numCuenta) {
        return modelFactory.eliminarCuenta(idCuenta, numCuenta);
    }

    /**
     * Metodo para actualizar una cuenta de la lista de cuentas billetera virtual
     * @param cuentaVieja Cuenta a actualizar
     * @param cuentaNueva Cuenta con los datos nuevos
     * @return Booleano sobre si la accion fue completada exitosamente o no
     */
    public boolean actualizarCuenta(CuentaDto cuentaVieja, CuentaDto cuentaNueva) {
        return modelFactory.actualizarCuenta(cuentaVieja, cuentaNueva);
    }

    /**
     * Metodo para obtener una lista de numeros de id de los usuario de la billetera virtual
     * @return Lista de numeros de id
     */
    public LinkedList<String> obtenerUsuariosId() {
        return modelFactory.obtenerUsuariosId();
    }

    /**
     * Metodo para verificar si existe una cuenta con el mismo numero de cuenta administrado
     * @param numCuenta Numero de cuenta administrado
     * @return Booleano sobre si se cumple la condicion o no
     */
    public boolean verificarCuentaNumCuenta(String numCuenta) {
        return modelFactory.verificarCuentaNumCuenta(numCuenta);
    }

    /**
     * Metodo para verificar si existe una cuenta con el mismo id administrado
     * @param id Id de la cuenta administrada
     * @return Booleano sobre si se cumple la condicion o no
     */
    public boolean verificarCuentaId(int id) {
        return modelFactory.verificarCuentaId(id);
    }
}