package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model;

import java.util.LinkedList;

public class Categoria {
    private BilleteraVirtual billeteraVirtual;
    private int idCategoria;
    private Usuario usuarioAsociado;
    private String nombre, descripcionOpcional;
    private Presupuesto presupuestoAsignado;
    private LinkedList<Transaccion> listaTransacciones;

    public Categoria() {
        this.listaTransacciones = new LinkedList<>();
    }

    public Categoria(int idCategoria, String nombre, String descripcionOpcional, Presupuesto presupuesto,
                     Usuario usuario,BilleteraVirtual billeteraVirtual) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.usuarioAsociado = usuario;
        this.presupuestoAsignado = presupuesto;
        this.descripcionOpcional = descripcionOpcional;
        this.billeteraVirtual = billeteraVirtual;
        this.listaTransacciones = new LinkedList<>();
    }

    public BilleteraVirtual getBilleteraVirtual() {
        return billeteraVirtual;
    }

    public void setBilleteraVirtual(BilleteraVirtual billeteraVirtual) {
        this.billeteraVirtual = billeteraVirtual;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionOpcional() {
        return descripcionOpcional;
    }

    public void setDescripcionOpcional(String descripcionOpcional) {
        this.descripcionOpcional = descripcionOpcional;
    }

    public LinkedList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(LinkedList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    public Presupuesto getPresupuestoAsignado() {
        return presupuestoAsignado;
    }

    public void setPresupuestoAsignado(Presupuesto presupuestoAsignado) {
        this.presupuestoAsignado = presupuestoAsignado;
    }
}