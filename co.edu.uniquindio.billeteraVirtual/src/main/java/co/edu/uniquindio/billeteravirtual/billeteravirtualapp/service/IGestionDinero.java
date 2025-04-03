package co.edu.uniquindio.billeteravirtual.service;

public interface IGestionDinero {
    boolean agregarDinero(double dinero, int idCuenta);
    boolean retirarDinero(double dinero, int idCuenta);
    boolean transferirDinero(double dinero, int idCuentaOrigen, int idCuentaDestino);
}