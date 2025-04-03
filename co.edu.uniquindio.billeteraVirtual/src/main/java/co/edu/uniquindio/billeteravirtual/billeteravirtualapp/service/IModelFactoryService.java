package co.edu.uniquindio.billeteravirtual.service;

import co.edu.uniquindio.billeteravirtual.mapping.dto.UsuarioDto;

import java.util.LinkedList;

public interface IModelFactoryService {
    LinkedList<UsuarioDto> obtenerUsuarios();
}