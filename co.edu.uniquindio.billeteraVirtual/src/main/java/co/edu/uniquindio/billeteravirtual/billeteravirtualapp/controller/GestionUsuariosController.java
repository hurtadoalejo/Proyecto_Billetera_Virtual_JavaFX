package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;

import java.util.LinkedList;

public class GestionUsuariosController {
    ModelFactory modelFactory;

    /**
     * Metodo constructor de la clase GestionUsuariosController
     */
    public GestionUsuariosController() {
        modelFactory = ModelFactory.getInstance();
    }

    /**
     * Metodo para obtener la lista de usuarios de billetera virtual
     * @return Lista de usuarios
     */
    public LinkedList<UsuarioDto> obtenerUsuarios() {
        return modelFactory.obtenerUsuarios();
    }

    /**
     * Metodo para agregar un usuario a la lista de usuarios de la billetera virtual
     * @param usuario Usuario a agregar
     * @return Booleano sobre si la accion fue completada exitosamente o no
     */
    public boolean agregarUsuario(UsuarioDto usuario) {
        return modelFactory.agregarUsuario(usuario);
    }

    /**
     * Metodo para eliminar un usuario de la lista de usuarios de la billetera virtual
     * @param id Id del usuario a eliminar
     * @return Booleano sobre si la accion fue completada exitosamente o no
     */
    public boolean eliminarUsuario(String id) {
        return modelFactory.eliminarUsuario(id);
    }

    /**
     * Metodo par aactualizar un usuario de la lista de usuarios de la billetera virtual
     * @param idUsuario id del usuario a actualizar
     * @param usuarioNuevo Usuario con los datos nuevos
     * @return Booleano sobre si la accion fue completada exitosamente o no
     */
    public boolean actualizarUsuario(String idUsuario, UsuarioDto usuarioNuevo) {
        return modelFactory.actualizarUsuario(idUsuario, usuarioNuevo);
    }
}