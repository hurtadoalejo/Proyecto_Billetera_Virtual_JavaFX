package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;

import java.util.LinkedList;

public class GestionCuentasUsuarioController {
    ModelFactory modelFactory;

    /**
     * Metodo constructor de la clase GestionCuentasUsuarioController
     */
    public GestionCuentasUsuarioController() {
        modelFactory = ModelFactory.getInstance();
    }

    /**
     * Metodo para obtener la lista de cuentas de un usuario
     * @param idUsuario Id del usuario
     * @return Lista de cuentas
     */
    public LinkedList<CuentaDto> obtenerCuentas(String idUsuario) {
        return modelFactory.obtenerCuentasUsuario(idUsuario);
    }

    /**
     * Metodo para agregar una cuenta a la lista de cuentas de un usuario
     * @param idUsuario Id del usuario
     * @param cuentaDto Cuenta a agregar
     * @return Booleano sobre si la accion fue completada exitosamente o no
     */
    public boolean agregarCuentaUsuario(String idUsuario, CuentaDto cuentaDto) {
        return modelFactory.agregarCuentaUsuario(idUsuario, cuentaDto);
    }

    /**
     * Metodo para eliminar una cuenta de la lista de cuentas de un usuario
     * @param idUsuario Id del usuario
     * @param idCuenta Id de la cuenta a eliminar
     * @param numCuenta Numero de cuenta a eliminar
     * @return Booleano sobre si la accion fue completada exitosamente o no
     */
    public boolean eliminarCuentaUsuario(String idUsuario, int idCuenta, String numCuenta) {
        return modelFactory.eliminarCuentaUsuario(idUsuario, idCuenta, numCuenta);
    }

    /**
     * Metodo para actualizar una cuenta de la lista de cuentas billetera virtual
     * @param idUsuario Id del usuario
     * @param cuentaVieja Cuenta a actualizar
     * @param cuentaNueva Cuenta con los datos nuevos
     * @return Booleano sobre si la accion fue completada exitosamente o no
     */
    public boolean actualizarCuentaUsuario(String idUsuario, CuentaDto cuentaVieja, CuentaDto cuentaNueva) {
        return modelFactory.actualizarCuentaUsuario(idUsuario, cuentaVieja, cuentaNueva);
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