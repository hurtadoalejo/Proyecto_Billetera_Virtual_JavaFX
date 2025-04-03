package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Presupuesto;

public interface ICrudPresupuesto {
    boolean crearPresupuesto(Presupuesto presupuesto);
    boolean eliminarPresupuesto(int idPresupuesto);
    boolean actualizarPresupuesto(int id, Presupuesto nuevoPresupuesto);
    Presupuesto obtenerPresupuesto(int id);
}