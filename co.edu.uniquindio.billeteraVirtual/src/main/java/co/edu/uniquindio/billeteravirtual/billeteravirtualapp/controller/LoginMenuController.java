package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;

public class LoginMenuController {
    ModelFactory modelFactory;

    public LoginMenuController() {
        modelFactory = ModelFactory.getInstance();
    }

    public boolean verificarCredencialesUsuario(String id, int clave) {
        return modelFactory.verificarCredencialesUsuario(id, clave);
    }

    public UsuarioDto obtenerUsuario(String id) {
        return modelFactory.obtenerUsuarioToUsuarioDto(id);
    }

    public boolean verificarCredencialesAdmin(int clave) {
        return modelFactory.verificarClaveAdmin(clave);
    }
}