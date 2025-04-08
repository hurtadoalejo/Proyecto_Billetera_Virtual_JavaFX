package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;

public class LoginMenuUsuarioController {
    ModelFactory modelFactory;

    /**
     * Metodo constructor de la clase LoginMenuUsuarioController
     */
    public LoginMenuUsuarioController() {
        modelFactory = ModelFactory.getInstance();
    }

    /**
     * Metodo para verificar las credenciales de un usuario
     * @param id Id del usuario administrada
     * @param clave Clave del usuario administrada
     * @return Booleano sobre si las credenciales son correctas o no
     */
    public boolean verificarCredencialesUsuario(String id, int clave) {
        Usuario usuario = modelFactory.verificarCredencialesUsuario(id, clave);
        if (usuario != null) {
            return true;
        }
        return false;
    }
}