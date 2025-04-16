package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CategoriaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Categoria;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Presupuesto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.ICategoriaMapping;

import java.util.LinkedList;

public class CategoriaMappingImpl implements ICategoriaMapping {

    @Override
    public LinkedList<CategoriaDto> getListaCategorias(LinkedList<Categoria> listaCategorias) {
        if (listaCategorias == null) {
            return null;
        }
        LinkedList<CategoriaDto> listaCategoriasDto = new LinkedList<>();
        for (Categoria categoria : listaCategorias) {
            listaCategoriasDto.add(categoriaToCategoriaDto(categoria));
        }
        return listaCategoriasDto;
    }

    @Override
    public CategoriaDto categoriaToCategoriaDto(Categoria categoria) {
        return new CategoriaDto(categoria.getIdCategoria(), categoria.getUsuarioAsociado().getIdUsuario(),
                categoria.getNombre(), categoria.getDescripcionOpcional(),
                mapNombrePresupuesto(categoria.getPresupuestoAsignado()));
    }

    @Override
    public Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto, BilleteraVirtual billeteraVirtual,
                                             Usuario usuario, Presupuesto presupuesto) {
        Categoria categoria = new Categoria();
        categoria.setBilleteraVirtual(billeteraVirtual);
        categoria.setUsuarioAsociado(usuario);
        categoria.setPresupuestoAsignado(presupuesto);
        categoria.setNombre(categoriaDto.nombre());
        categoria.setDescripcionOpcional(categoriaDto.descripcion());
        categoria.setIdCategoria(categoriaDto.idCategoria());
        return categoria;
    }

    @Override
    public String mapNombrePresupuesto(Presupuesto presupuesto) {
        return (presupuesto != null) ? presupuesto.getNombre() : null;
    }
}