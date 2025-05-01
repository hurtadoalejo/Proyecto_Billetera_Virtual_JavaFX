package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.TipoPresupuesto;

public record PresupuestoDto(
        int idPresupuesto, String nombre, double montoTotalAsignado,
        double montoGastado, String idUsuario, String cuentaAsociada,
        TipoPresupuesto tipoPresupuesto
) {
}
