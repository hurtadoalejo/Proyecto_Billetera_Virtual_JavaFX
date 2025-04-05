package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.IGestionDinero;

import java.util.LinkedList;

public class Usuario implements IGestionDinero {
    private BilleteraVirtual billeteraVirtual;
    private String nombreCompleto, idUsuario, correoElectronico, numeroTelefono, direccion;
    private int clave;
    private double saldoTotal;
    private LinkedList<Cuenta> listaCuentas;
    private LinkedList<Presupuesto> listaPresupuestos;
    private LinkedList<Transaccion> listaTransacciones;

    public Usuario(){
        listaCuentas = new LinkedList<>();
        listaPresupuestos = new LinkedList<>();
        listaTransacciones = new LinkedList<>();
    }

    public Usuario(String nombreCompleto, String idUsuario, String correoElectronico, String numeroTelefono, String direccion, int clave, BilleteraVirtual billeteraVirtual) {
        this.nombreCompleto = nombreCompleto;
        this.idUsuario = idUsuario;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.direccion = direccion;
        this.clave = clave;
        this.billeteraVirtual = billeteraVirtual;
        listaCuentas = new LinkedList<>();
        listaPresupuestos = new LinkedList<>();
        listaTransacciones = new LinkedList<>();
    }

    public BilleteraVirtual getBilleteraVirtual() {
        return billeteraVirtual;
    }

    public void setBilleteraVirtual(BilleteraVirtual billeteraVirtual) {
        this.billeteraVirtual = billeteraVirtual;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public LinkedList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(LinkedList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public LinkedList<Presupuesto> getListaPresupuestos() {
        return listaPresupuestos;
    }

    public void setListaPresupuestos(LinkedList<Presupuesto> listaPresupuestos) {
        this.listaPresupuestos = listaPresupuestos;
    }

    public LinkedList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(LinkedList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    @Override
    public boolean agregarDinero(double dinero, int idCuenta) {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getIdCuenta() == idCuenta) {
                Transaccion transaccion = Transaccion.builder()
                        .billeteraVirtual(billeteraVirtual)
                        .idTransaccion(billeteraVirtual.getListaTransacciones().size() + 1)
                        .build();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean retirarDinero(double dinero, int idCuenta) {
        return false;
    }

    @Override
    public boolean transferirDinero(double dinero, int idCuentaOrigen, int idCuentaDestino) {
        return false;
    }
}