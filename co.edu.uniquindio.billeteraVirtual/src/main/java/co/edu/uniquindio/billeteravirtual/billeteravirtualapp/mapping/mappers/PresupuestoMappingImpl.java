package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.PresupuestoDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Categoria;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Presupuesto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.IPresupuestoMapping;

import java.util.LinkedList;

public class PresupuestoMappingImpl implements IPresupuestoMapping {

    @Override
    public LinkedList<PresupuestoDto> getPresupuestosDto(LinkedList<Presupuesto> listaPresupuestos) {
        if (listaPresupuestos == null) {
            listaPresupuestos = new LinkedList<>();
        }
        LinkedList<PresupuestoDto> listaPresupuestosDto = new LinkedList<>();
        for (Presupuesto presupuesto : listaPresupuestos) {
            listaPresupuestosDto.add(presupuestoToPresupuestoDto(presupuesto));
        }
        return listaPresupuestosDto;
    }

    @Override
    public PresupuestoDto presupuestoToPresupuestoDto(Presupuesto presupuesto) {
       return new PresupuestoDto(presupuesto.getIdPresupuesto(), presupuesto.getNombre(),
               presupuesto.getMontoTotalAsignado(), presupuesto.getMontoGastado(),
               presupuesto.getUsuarioAsociado().getIdUsuario(),
               presupuesto.getCategoriaPresupuesto().getNombre());
    }

    @Override
    public Presupuesto presupuestoDtoToPresupuesto(PresupuestoDto presupuestoDto, BilleteraVirtual billeteraVirtual,
                                                   Usuario usuario, Categoria categoria) {
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setBilleteraVirtual(billeteraVirtual);
        presupuesto.setIdPresupuesto(presupuestoDto.idPresupuesto());
        presupuesto.setNombre(presupuestoDto.nombre());
        presupuesto.setMontoTotalAsignado(presupuestoDto.montoTotalAsignado());
        presupuesto.setMontoGastado(presupuestoDto.montoGastado());
        presupuesto.setUsuarioAsociado(usuario);
        presupuesto.setCategoriaPresupuesto(categoria);
        return presupuesto;
    }
}