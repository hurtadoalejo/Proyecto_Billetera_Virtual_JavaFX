package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model;

import java.time.LocalDate;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.builder.TransaccionBuilder;

public class Transaccion {
    private BilleteraVirtual billeteraVirtual;
    private int idTransaccion;
    private LocalDate fecha;
    private double monto;
    private String descripcionOpcional;
    private Usuario usuarioAsociado;
    private Categoria categoriaTransaccion;
    private Cuenta cuentaOrigen, cuentaDestino;

    public Transaccion(){}

    public Transaccion(int idTransaccion, LocalDate fecha, double monto, String descripcionOpcional, Usuario usuarioAsociado, Categoria categoriaTransaccion, Cuenta cuentaOrigen, Cuenta cuentaDestino, BilleteraVirtual billeteraVirtual) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.descripcionOpcional = descripcionOpcional;
        this.usuarioAsociado = usuarioAsociado;
        this.categoriaTransaccion = categoriaTransaccion;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.billeteraVirtual = billeteraVirtual;
    }

    public BilleteraVirtual getBilleteraVirtual() {
        return billeteraVirtual;
    }

    public void setBilleteraVirtual(BilleteraVirtual billeteraVirtual) {
        this.billeteraVirtual = billeteraVirtual;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcionOpcional() {
        return descripcionOpcional;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public Categoria getCategoriaTransaccion() {
        return categoriaTransaccion;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public static TransaccionBuilder builder(){
        return new TransaccionBuilder();
    }
}