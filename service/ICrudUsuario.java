package co.edu.uniquindio.billeteravirtual.service;

import co.edu.uniquindio.billeteravirtual.model.Usuario;

public interface ICrudUsuario {
    boolean crearUsuario(Usuario usuario);
    boolean eliminarUsuario(String id);
    boolean actualizarUsuario(String id, Usuario nuevoUsuario);
    Usuario obtenerUsuario(String id);
}