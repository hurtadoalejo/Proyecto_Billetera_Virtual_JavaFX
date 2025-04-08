package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;

public class LoginMenuAdminController {
    ModelFactory modelFactory;

    /**
     * Metodo constructor de la clase LoginMenuAdminController
     */
    public LoginMenuAdminController() {
        modelFactory = ModelFactory.getInstance();
    }

    /**
     * Metodo para verificar la clave del administrador
     * @param clave Clave administrado
     * @return Booleano sobre si la clave del admin es correcta o no
     */
    public boolean verificarClaveAdmin(int clave) {
        return modelFactory.verificarClaveAdmin(clave);
    }
}