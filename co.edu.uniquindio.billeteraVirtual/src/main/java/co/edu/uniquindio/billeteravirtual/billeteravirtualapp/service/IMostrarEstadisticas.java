package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;

public interface IMostrarEstadisticas {
    String gastoMasComun();
    Usuario usuarioMasTransacciones();
    double saldoPromedioUsuarios();
}