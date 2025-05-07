package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto;

public record PresupuestoDto(
        int idPresupuesto, String nombre, double montoTotalAsignado,
        double montoGastado, String idUsuario, String cuentaAsociada,
        String categoria
) {
}
