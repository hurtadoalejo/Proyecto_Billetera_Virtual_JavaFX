package co.edu.uniquindio.billeteravirtual.service;

import co.edu.uniquindio.billeteravirtual.model.Categoria;

public interface ICrudCategoria {
    boolean crearCategoria(Categoria categoria);
    boolean actualizarCategoria(int idCategoria, Categoria nuevaCategoria);
    boolean eliminarCategoria(int idCategoria);
    Categoria obtenerCategoria(int idCategoria);
}
