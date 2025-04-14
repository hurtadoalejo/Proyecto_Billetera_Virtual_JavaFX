package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;

public class EditarPerfilController {
    ModelFactory modelFactory;

    public EditarPerfilController() {
        modelFactory = ModelFactory.getInstance();
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