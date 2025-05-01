package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.PresupuestoDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;

import java.util.LinkedList;

public interface IPresupuestoMapping {
    LinkedList<PresupuestoDto> getPresupuestosDto(LinkedList<Presupuesto> listaPresupuestos);
    PresupuestoDto presupuestoToPresupuestoDto(Presupuesto presupuesto);
    Presupuesto presupuestoDtoToPresupuesto(PresupuestoDto presupuestoDto, BilleteraVirtual billeteraVirtual,
                                            Usuario usuario, Cuenta cuenta);
}