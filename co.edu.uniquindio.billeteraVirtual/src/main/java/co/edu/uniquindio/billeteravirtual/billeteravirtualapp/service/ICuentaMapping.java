package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;


import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Presupuesto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;

import java.util.LinkedList;

public interface ICuentaMapping {
    LinkedList<CuentaDto> getCuentasDto(LinkedList<Cuenta> listaCuentas);
    CuentaDto cuentaToCuentaDto(Cuenta cuenta);
    Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto, BilleteraVirtual billeteraVirtual,
                             Usuario usuario, Presupuesto presupuesto);
}