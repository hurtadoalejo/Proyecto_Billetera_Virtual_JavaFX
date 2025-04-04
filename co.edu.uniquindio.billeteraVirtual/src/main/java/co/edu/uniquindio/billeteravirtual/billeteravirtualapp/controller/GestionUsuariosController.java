package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;

import java.util.LinkedList;

public class GestionUsuariosController {
    ModelFactory modelFactory;

    public GestionUsuariosController() {
        modelFactory = ModelFactory.getInstance();
    }

    public LinkedList<UsuarioDto> obtenerUsuarios() {
        return modelFactory.obtenerUsuarios();
    }

    public boolean agregarUsuario(UsuarioDto usuario) {
        return modelFactory.agregarUsuario(usuario);
    }

    public boolean eliminarUsuario(UsuarioDto usuario) {
        return modelFactory.eliminarUsuario(usuario);
    }

    public boolean actualizarUsuario(UsuarioDto usuario, UsuarioDto usuarioNuevo) {
        return modelFactory.actualizarUsuario(usuario, usuarioNuevo);
    }
}