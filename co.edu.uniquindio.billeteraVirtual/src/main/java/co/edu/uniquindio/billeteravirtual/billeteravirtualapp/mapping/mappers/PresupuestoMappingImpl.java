package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.PresupuestoDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;
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
        String numCuenta = presupuesto.getCuentaAsociada() != null
                ? presupuesto.getCuentaAsociada().getNumeroCuenta() : null;

       return new PresupuestoDto(
               presupuesto.getIdPresupuesto(),
               presupuesto.getNombre(),
               presupuesto.getMontoTotalAsignado(),
               presupuesto.getMontoGastado(),
               presupuesto.getUsuarioAsociado().getIdUsuario(),
               numCuenta,
               presupuesto.getTipoPresupuesto());
    }

    @Override
    public Presupuesto presupuestoDtoToPresupuesto(PresupuestoDto presupuestoDto, BilleteraVirtual billeteraVirtual,
                                                   Usuario usuario, Cuenta cuenta) {
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setBilleteraVirtual(billeteraVirtual);
        presupuesto.setIdPresupuesto(presupuestoDto.idPresupuesto());
        presupuesto.setNombre(presupuestoDto.nombre());
        presupuesto.setMontoTotalAsignado(presupuestoDto.montoTotalAsignado());
        presupuesto.setMontoGastado(presupuestoDto.montoGastado());
        presupuesto.setUsuarioAsociado(usuario);
        presupuesto.setCuentaAsociada(cuenta);
        presupuesto.setTipoPresupuesto(presupuestoDto.tipoPresupuesto());
        return presupuesto;
    }

    public String mapNombreCuenta(Categoria categoria) {
        return (categoria != null) ? categoria.getNombre() : null;
    }
}