package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;

public abstract class CuentaFactory {
    public abstract Cuenta crearCuenta(int idCuenta, String nombreBanco, String numeroCuenta, Usuario usuarioAsociado, BilleteraVirtual billeteraVirtual, Presupuesto presupuestoAsociado);
}