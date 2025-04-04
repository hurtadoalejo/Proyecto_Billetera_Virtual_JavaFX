package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Transaccion;

public interface ICrudTransaccion {
    boolean agregarTransaccion(Transaccion transaccion);
    boolean eliminarTransaccion(int idTransaccion);
    boolean actualizarTransaccion(int idTransaccion, Transaccion nuevaTransaccion);
    Transaccion obtenerTransaccion(int idTransaccion);
}
