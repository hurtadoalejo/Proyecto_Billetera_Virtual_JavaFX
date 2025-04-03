package co.edu.uniquindio.billeteravirtual.model.builder;

import co.edu.uniquindio.billeteravirtual.model.*;

import java.time.LocalDate;

public class TransaccionBuilder {
    protected BilleteraVirtual billeteraVirtual;
    protected int idTransaccion;
    protected LocalDate fecha;
    protected double monto;
    protected String descripcionOpcional;
    protected TipoTransaccion tipoTransaccion;
    protected Usuario usuarioAsociado;
    protected Categoria categoriaTransaccion;
    protected Cuenta cuentaOrigen, cuentaDestino;

    public TransaccionBuilder idTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
        return this;
    }

    public TransaccionBuilder fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public TransaccionBuilder monto(double monto) {
        this.monto = monto;
        return this;
    }

    public TransaccionBuilder descripcionOpcional(String descripcionOpcional) {
        this.descripcionOpcional = descripcionOpcional;
        return this;
    }

    public TransaccionBuilder tipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
        return this;
    }

    public TransaccionBuilder usuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
        return this;
    }

    public TransaccionBuilder categoriaTransaccion(Categoria categoriaTransaccion) {
        this.categoriaTransaccion = categoriaTransaccion;
        return this;
    }

    public TransaccionBuilder cuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
        return this;
    }

    public TransaccionBuilder cuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
        return this;
    }

    public TransaccionBuilder billeteraVirtual(BilleteraVirtual billeteraVirtual) {
        this.billeteraVirtual = billeteraVirtual;
        return this;
    }

    public Transaccion build() {
        return new Transaccion(idTransaccion, fecha, monto, descripcionOpcional, tipoTransaccion, usuarioAsociado, categoriaTransaccion, cuentaOrigen, cuentaDestino, billeteraVirtual);
    }
}