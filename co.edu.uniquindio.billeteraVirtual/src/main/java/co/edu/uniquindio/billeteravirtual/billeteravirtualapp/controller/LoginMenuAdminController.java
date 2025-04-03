package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;

public class LoginMenuAdminController {
    ModelFactory modelFactory;

    public LoginMenuAdminController() {
        modelFactory = ModelFactory.getInstance();
    }

    public boolean verificarClaveAdmin(int clave) {
        if (modelFactory.verificarClaveAdmin(clave)){
            System.out.println("Clave admin encontrado");
        }
        return false;
    }
}