package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;

import java.util.LinkedList;

public interface IModelFactoryService {
    LinkedList<UsuarioDto> obtenerUsuarios();
}