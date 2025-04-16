package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CategoriaDto;

import java.util.LinkedList;

public class GestionCategoriasController {
    ModelFactory modelFactory;

    public GestionCategoriasController() {
        modelFactory = ModelFactory.getInstance();
    }

    public LinkedList<CategoriaDto> obtenerCategorias(String idUsuario) {
        return modelFactory.obtenerCategorias(idUsuario);
    }

    public boolean agregarCategoria(CategoriaDto categoria, String idUsuario) {
        return modelFactory.agregarCategoria(categoria, idUsuario);
    }

    public boolean actualizarCategoria(String idUsuario, int idCategoriaVieja, CategoriaDto categoriaNueva) {
        return modelFactory.actualizarCategoria(idUsuario, idCategoriaVieja, categoriaNueva);
    }

    public boolean eliminarCategoria(String idUsuario, int idCategoria) {
        return modelFactory.eliminarCategoria(idUsuario, idCategoria);
    }

    public LinkedList<String> obtenerPresupuestosNombres(String idUsuario) {
        return modelFactory.obtenerPresupuestosNombres(idUsuario);
    }
}