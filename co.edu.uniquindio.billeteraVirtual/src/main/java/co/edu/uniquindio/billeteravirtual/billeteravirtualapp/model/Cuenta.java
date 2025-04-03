package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model;

import java.util.LinkedList;

public class Cuenta {
    private BilleteraVirtual billeteraVirtual;
    private int idCuenta;
    private String nombreBanco, numeroCuenta;
    private Usuario usuarioAsociado;
    private TipoCuenta tipoCuenta;
    private LinkedList<Transaccion> listaTransacciones;
    private double saldo;

    public Cuenta(){}

    public Cuenta(int idCuenta, String nombreBanco, String numeroCuenta, Usuario usuarioAsociado, TipoCuenta tipoCuenta, BilleteraVirtual billeteraVirtual) {
        this.idCuenta = idCuenta;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.usuarioAsociado = usuarioAsociado;
        this.tipoCuenta = tipoCuenta;
        this.billeteraVirtual = billeteraVirtual;
        this.saldo = 0;
    }

    public BilleteraVirtual getBilleteraVirtual() {
        return billeteraVirtual;
    }

    public void setBilleteraVirtual(BilleteraVirtual billeteraVirtual) {
        this.billeteraVirtual = billeteraVirtual;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public LinkedList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(LinkedList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}