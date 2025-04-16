package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CategoriaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Categoria;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Presupuesto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;

import java.util.LinkedList;

public interface ICategoriaMapping {
    LinkedList<CategoriaDto> getListaCategorias(LinkedList<Categoria> listaCategorias);

    CategoriaDto categoriaToCategoriaDto(Categoria categoria);

    Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto, BilleteraVirtual billeteraVirtual, Usuario usuario);

    String mapNombrePresupuesto(Presupuesto presupuesto);
}