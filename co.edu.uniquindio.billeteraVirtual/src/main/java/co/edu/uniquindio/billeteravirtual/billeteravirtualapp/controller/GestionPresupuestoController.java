package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.PresupuestoDto;

import java.util.LinkedList;

public class GestionPresupuestoController {
    ModelFactory modelFactory;

    public GestionPresupuestoController() {
        this.modelFactory = ModelFactory.getInstance();
    }

    public LinkedList<PresupuestoDto> obtenerPresupuestos(String idUsuario) {
        return modelFactory.obtenerPresupuestos(idUsuario);
    }

    public boolean agregarPresupuesto(PresupuestoDto presupuestoDto) {
        return modelFactory.agregarPresupuesto(presupuestoDto);
    }

    public boolean eliminarPresupuesto(int idPresupuesto, String idUsuario) {
        return modelFactory.eliminarPresupuesto(idPresupuesto, idUsuario);
    }

    public boolean actualizarPresupuesto(int idPresupuestoViejo, PresupuestoDto presupuestoDto){
        return modelFactory.actualizarPresupuesto(idPresupuestoViejo, presupuestoDto);
    }

    public LinkedList<String> obtenerCategoriasDisponibles(String idUsuario) {
        return modelFactory.obtenerCategoriasDisponibles(idUsuario);
    }
}