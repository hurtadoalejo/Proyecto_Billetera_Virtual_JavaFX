package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.TipoCuenta;

public record CuentaDto(
        int idCuenta, String nombreBanco, String numCuenta, String idUsuarioAsociado, TipoCuenta tipoCuenta
) {
}