package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.TipoTransaccion;

import java.time.LocalDate;

public record TransaccionDto(
        int idTransaccion, LocalDate fecha, double monto,
        String descripcion, String idUsuario, String numCuentaOrigen,
        String numCuentaDestino, TipoTransaccion tipoTransaccion
) {
}