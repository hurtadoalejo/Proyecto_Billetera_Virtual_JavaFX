package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;

public class MenuAdminController {
    ModelFactory modelFactory;

    /**
     * Metodo constructor de la clase MenuAdminController
     */
    public MenuAdminController() {
        modelFactory = ModelFactory.getInstance();
    }
}