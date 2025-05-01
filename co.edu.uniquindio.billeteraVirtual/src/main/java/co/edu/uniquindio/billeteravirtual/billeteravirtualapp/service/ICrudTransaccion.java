package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Transaccion;

public interface ICrudTransaccion {
    boolean agregarTransaccion(Transaccion transaccion);
    Transaccion obtenerTransaccion(int idTransaccion);
}
