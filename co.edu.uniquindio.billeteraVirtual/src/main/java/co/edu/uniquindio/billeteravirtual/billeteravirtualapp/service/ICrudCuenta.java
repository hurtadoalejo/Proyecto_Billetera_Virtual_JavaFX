package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Cuenta;

public interface ICrudCuenta {
    boolean agregarCuenta(Cuenta cuenta, String idUsuario);
    boolean eliminarCuenta(int id, String numCuenta);
    boolean actualizarCuenta(Cuenta cuentaVieja, String idUsuarioViejo, String idUsuarioNuevo, Cuenta nuevaCuenta);
    Cuenta obtenerCuenta(int id, String numCuenta);
}
