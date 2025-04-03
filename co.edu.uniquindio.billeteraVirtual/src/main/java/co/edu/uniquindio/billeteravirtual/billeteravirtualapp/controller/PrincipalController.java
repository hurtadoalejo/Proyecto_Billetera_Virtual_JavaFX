package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;

public class PrincipalController {
    private ModelFactory modelFactory;

    public PrincipalController() {
        modelFactory = ModelFactory.getInstance();
    }

    public boolean verificarClaveAdmin(int clave){
        return modelFactory.verificarClaveAdmin(clave);
    }
}