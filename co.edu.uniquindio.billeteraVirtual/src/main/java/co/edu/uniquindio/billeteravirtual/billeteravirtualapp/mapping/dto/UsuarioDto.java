package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto;

public record UsuarioDto (
        String nombreCompleto, String idUsuario, String correoElectronico,
        String numeroTelefono, String direccion, int clave
){
}