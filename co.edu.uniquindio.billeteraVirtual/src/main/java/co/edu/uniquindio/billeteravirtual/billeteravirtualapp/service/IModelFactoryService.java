package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CategoriaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;

import java.util.LinkedList;

public interface IModelFactoryService {
    LinkedList<UsuarioDto> obtenerUsuarios();
    LinkedList<CuentaDto> obtenerCuentas();
    LinkedList<CategoriaDto> obtenerCategorias();
}