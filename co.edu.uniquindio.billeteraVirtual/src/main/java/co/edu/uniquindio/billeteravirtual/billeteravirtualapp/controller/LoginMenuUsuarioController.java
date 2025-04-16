package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;

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
     * @return Booleano sobre si las credenciales son correctas
     */
    public boolean verificarCredencialesUsuario(String id, int clave) {
        return modelFactory.verificarCredencialesUsuario(id, clave);
    }

    /**
     * Metodo para obtener un usuario dado un id
     * @param id Id del usuario a buscar
     * @return UsuarioDto
     */
    public UsuarioDto obtenerUsuario(String id) {
        return modelFactory.obtenerUsuarioToUsuarioDto(id);
    }
}