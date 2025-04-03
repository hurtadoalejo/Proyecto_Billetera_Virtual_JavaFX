package co.edu.uniquindio.billeteravirtual.service;

import co.edu.uniquindio.billeteravirtual.model.Presupuesto;

public interface ICrudPresupuesto {
    boolean crearPresupuesto(Presupuesto presupuesto);
    boolean eliminarPresupuesto(int idPresupuesto);
    boolean actualizarPresupuesto(int id, Presupuesto nuevoPresupuesto);
    Presupuesto obtenerPresupuesto(int id);
}