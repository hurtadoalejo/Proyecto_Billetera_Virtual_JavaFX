package co.edu.uniquindio.billeteravirtual.service;

import co.edu.uniquindio.billeteravirtual.model.Transaccion;

public interface ICrudTransaccion {
    boolean crearTransaccion(Transaccion transaccion);
    boolean eliminarTransaccion(int idTransaccion);
    boolean actualizarTransaccion(int idTransaccion, Transaccion nuevaTransaccion);
    Transaccion obtenerTransaccion(int idTransaccion);
}
