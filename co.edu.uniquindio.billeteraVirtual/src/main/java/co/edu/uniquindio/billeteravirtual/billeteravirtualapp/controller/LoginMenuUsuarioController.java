package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;

public class LoginMenuUsuarioController {
    ModelFactory modelFactory;

    public LoginMenuUsuarioController() {
        modelFactory = ModelFactory.getInstance();
    }

    public boolean verificarCredencialesUsuario(String id, int clave) {
        Usuario usuario = modelFactory.verificarCredencialesUsuario(id, clave);
        if (usuario != null) {
            System.out.println("Hola " + usuario.getNombreCompleto());
        }
        return false;
    }
}