package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Cuenta;

public interface ICrudCuenta {
    boolean agregarCuenta(Cuenta cuenta);
    boolean eliminarCuenta(int id);
    boolean actualizarCuenta(int id, Cuenta nuevaCuenta);
    Cuenta obtenerCuenta(int id);
}
