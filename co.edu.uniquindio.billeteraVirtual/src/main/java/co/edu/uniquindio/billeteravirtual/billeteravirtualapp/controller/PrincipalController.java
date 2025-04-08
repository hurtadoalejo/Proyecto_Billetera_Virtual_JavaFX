package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;

public class PrincipalController {
    private ModelFactory modelFactory;

    /**
     * Metodo constructor de la clase PrincipalController
     */
    public PrincipalController() {
        modelFactory = ModelFactory.getInstance();
    }


}