package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Categoria;

public interface ICrudCategoria {
    boolean crearCategoria(Categoria categoria);
    boolean actualizarCategoria(int idCategoria, Categoria nuevaCategoria);
    boolean eliminarCategoria(int idCategoria);
    Categoria obtenerCategoria(int idCategoria);
}
