package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;

public class FactoryCuentaAhorro extends CuentaFactory{
    @Override
    public Cuenta crearCuenta(int idCuenta, String nombreBanco, String numeroCuenta, Usuario usuarioAsociado, BilleteraVirtual billeteraVirtual, Presupuesto presupuestoAsociado) {
        return new Cuenta(idCuenta, nombreBanco, numeroCuenta, usuarioAsociado, TipoCuenta.AHORRO, billeteraVirtual, presupuestoAsociado);
    }
}