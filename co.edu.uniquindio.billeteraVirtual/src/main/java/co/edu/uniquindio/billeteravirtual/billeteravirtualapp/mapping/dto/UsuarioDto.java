package co.edu.uniquindio.billeteravirtual.mapping.dto;

import co.edu.uniquindio.billeteravirtual.model.Cuenta;
import co.edu.uniquindio.billeteravirtual.model.Presupuesto;
import co.edu.uniquindio.billeteravirtual.model.Transaccion;

import java.util.LinkedList;

public class UsuarioDto {
    private String nombreCompleto, idUsuario, correoElectronico, numeroTelefono, direccion;
    private int clave;
    private double saldoTotal;
    private LinkedList<Integer> listaCuentasId;
    private LinkedList<Integer> listaPresupuestosId;
    private LinkedList<Integer> listaTransaccionesId;
}
